package cn.edu.aust.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.edu.aust.assemble.UserAssemble;
import cn.edu.aust.common.constant.PosCode;
import cn.edu.aust.common.entity.ResultVO;
import cn.edu.aust.common.entity.Setting;
import cn.edu.aust.common.service.JedisClient;
import cn.edu.aust.common.util.SystemUtil;
import cn.edu.aust.dto.UserDTO;
import cn.edu.aust.dto.UserRankDTO;
import cn.edu.aust.mapper.UserMapper;
import cn.edu.aust.pojo.entity.UserDO;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户service类
 *
 * @author Niu Li
 * @date 2017/1/22
 */
@Service
@Slf4j
public class UserService {

  private static final ModelMapper modelMapper = new ModelMapper();
  @Resource
  private JedisClient jedisClient;
  @Resource
  private UserMapper userMapper;
  @Resource
  private SettingService settingService;
  @Resource
  private MailService mailService;

  /**
   * 得到当前客户端登录用户
   * todo 替换为spring security
   * @return 该用户
   */
  public UserDO getCurrent() {
    ServletRequestAttributes context = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    UserDTO userDTO = (UserDTO) context.getAttribute("userinfo",
        RequestAttributes.SCOPE_SESSION);
    Long id = userDTO != null ? userDTO.getId() : null;
    return id == null ? null : userMapper.selectByPrimaryKey(id);
  }

  /**
   * 根据email查询用户
   *
   * @param email 邮箱
   * @return 用户
   */
  public UserDTO findByEmail(String email) {
    UserDO userDO = userMapper.findByEmail(email);
    return UserAssemble.user2UserDTO(userDO);
  }

  /**
   * 用户注册
   * @return 注册后用户
   */
  @Transactional
  public UserDTO registerUser(String passwd, String ip, String nickname, String email){
    UserDO userDO = new UserDO();
    userDO.setPassword(DigestUtils.sha256Hex(passwd));
    userDO.setCreatedate(new Date());
    userDO.setModifydate(userDO.getCreatedate());
    userDO.setIp(ip);
    userDO.setNickname(nickname);
    userDO.setEmail(email);
    userDO.setUsername(email);
    userDO.setIsDefunct(2);//设置待验证状态
    userMapper.insert(userDO);
    //发送邮件,验证
    mailService.sendRegister(email, jedisClient);
    return UserAssemble.user2UserDTO(userDO);
  }

  /**
   * 检验Email验证
   * @param token 验证戳
   * @param resultVO 返回封装类
   * @return true成功
   */
  public Boolean checkEmailToken(String token, ResultVO resultVO){
    String email = jedisClient.get(token);
    if (StringUtils.isEmpty(email)) {
      resultVO.buildWithPosCode(PosCode.URL_ERROR);
      return false;
    }
    //判断用户状态
    UserDO userDO = userMapper.findByEmail(email);
    if (userDO.getId() == null || userDO.getIsDefunct() != 2) {
      resultVO.buildWithPosCode(PosCode.ALREADY_REGISTER);
      return false;
    }
    //更新用户 已验证
    userDO.setIsDefunct(0);
    userDO.setModifydate(new Date());
    userMapper.updateByPrimaryKeySelective(userDO);
    jedisClient.del(token);
    return true;
  }
  /**
   * 检查用户是否可登陆
   *
   * @param userDTO  用户
   * @param passwd   用户密码(明文)
   * @param ip       登陆ip
   * @param resultVO 错误写回实体
   * @return true 可登陆
   */
  public Boolean checkCanLogin(UserDTO userDTO, String passwd, String ip, ResultVO resultVO) {
    UserDO userDO = userMapper.selectByPrimaryKey(userDTO.getId());
    Setting setting = settingService.getSetting();
    //验证是否冻结
    if (userDO.getIsDefunct() == 1) {
      resultVO.buildWithPosCode(PosCode.USER_FREEZE);
      return false;
    }
    //验证锁定状态
    if (userDO.getIsLock() == 1) {
      int accountLockTime = setting.getAccountLockTime();
      //锁定时间0,则永久锁定
      if (accountLockTime == 0) {
        resultVO.buildWithPosCode(PosCode.USER_LOCKED);
        return false;
      }
      Date lockdate = userDO.getLockdate();
      Date unlockdate = DateUtils.addMinutes(lockdate, accountLockTime);
      if (new Date().after(unlockdate)) {
        userDO.setIsLock(0);
        userDO.setLoginfail(0);
        userDO.setLockdate(null);
        userMapper.updateByPrimaryKeySelective(userDO);
      } else {
        resultVO.buildWithPosCode(PosCode.USER_LOCKED);
        return false;
      }
    }
    //验证密码
    if (!userDO.getPassword().equals(DigestUtils.sha256Hex(passwd.trim()))) {
      int accountLockCount = userDO.getLoginfail() + 1;
      if (accountLockCount > setting.getAccountLockCount()) {
        userDO.setLockdate(new Date());
        userDO.setIsLock(1);
        log.info("用户:{}已被锁定", userDO.getEmail());
      }
      userDO.setLoginfail(accountLockCount);
      userMapper.updateByPrimaryKeySelective(userDO);
      if (userDO.getIsLock() == 1) {
        resultVO.buildWithPosCode(PosCode.USER_LOCKED);
        return false;
      } else {
        String msg = "密码错误,若再错误" + (setting.getAccountLockCount() - accountLockCount + 1) + "次,则锁定账户";
        resultVO.buildWithMsgAndStatus(PosCode.USER_LOCKED, msg);
        return false;
      }
    }
    //登录成功
    userDO.setIp(ip);
    userDO.setModifydate(new Date());
    userDO.setLoginfail(0);
    userMapper.updateByPrimaryKeySelective(userDO);
    return true;
  }

  /**
   * 查询用户排名
   *
   * @return 排名后的用户
   */
  public List<UserRankDTO> queryForRank() {
    List<UserDO> userDOS = userMapper.queryForRank();
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(userDOS, new TypeToken<List<UserRankDTO>>() {
    }.getType());
  }

  /**
   * 查询展示到首页的用户
   *
   * @return 展示到首页用户
   */
  public List<UserDTO> queryToIndexShow() {
    List<UserDO> userDOS = userMapper.queryToShow();
    if (CollectionUtils.isEmpty(userDOS)) {
      return Collections.emptyList();
    }
    return modelMapper.map(userDOS, new TypeToken<List<UserDTO>>() {
    }.getType());
  }

  /**
   * 判断用户名或者邮箱是否存在
   *
   * @param username 用户名
   * @param email    邮箱
   * @return true存在
   */
  public boolean judgeUsernameOrEmail(String username, String email) {
    UserDO userDO = new UserDO();
    if (StringUtils.isNoneEmpty(username)) {
      userDO.setUsername(username);
      userDO = userMapper.selectOne(userDO);
      if (userDO.getId() != null) {
        return true;
      }
    }
    if (StringUtils.isNoneEmpty(email)) {
      userDO.setEmail(email);
      userDO = userMapper.selectOne(userDO);
    }
    return userDO != null;
  }

  /**
   * 判断用户名是否被禁用
   *
   * @param username 用户名
   * @return true被禁用
   */
  public boolean usernameIsDisabled(String username) {
    if (StringUtils.isEmpty(username)) return false;

    Setting setting = SystemUtil.getSetting(jedisClient);
    String[] disabledName = setting.getDisabledUsernames().split(",");
    for (String s : disabledName) {
      if (StringUtils.equalsIgnoreCase(s, username)) {
        return true;
      }
    }
    return false;
  }

}
