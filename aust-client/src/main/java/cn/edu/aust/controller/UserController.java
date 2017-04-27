package cn.edu.aust.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import cn.edu.aust.common.constant.CodeType;
import cn.edu.aust.common.constant.PosCode;
import cn.edu.aust.common.constant.RedisKey;
import cn.edu.aust.common.entity.ResultVO;
import cn.edu.aust.common.util.LanguageUtil;
import cn.edu.aust.common.util.WebUtils;
import cn.edu.aust.convert.UserConvert;
import cn.edu.aust.dto.UserDTO;
import cn.edu.aust.plugin.storage.StoragePlugin;
import cn.edu.aust.pojo.entity.UserDO;
import cn.edu.aust.service.SolutionService;
import cn.edu.aust.service.UserService;
import cn.edu.aust.vo.UserInfoVO;
import cn.edu.aust.vo.UserRankVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Niu Li
 * @since  2017/1/26
 */
@RestController
@Slf4j
public class UserController {

  @Resource
  private UserService userService;
  @Resource
  private SolutionService solutionService;
  @Resource
  private StringRedisTemplate redisTemplate;
  @Resource
  private StoragePlugin storagePlugin;
  /**
   * 头像限制类型
   */
  private static final Map<String,String> imageSuffixs = Maps.newHashMap();

  static{
    imageSuffixs.put("image/png",".png");
    imageSuffixs.put("image/jpeg",".jpg");
    imageSuffixs.put("image/gif",".gif");
  }

  /**
   * 获取首页展示用户
   */
  @GetMapping(value = "/index/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO<?> indexToShow() {
    List<UserRankVO> userRanks = userService.queryToIndexShow()
        .stream().limit(6).map(UserRankVO::assemble)
        .collect(Collectors.toList());
    return new ResultVO<>().buildOKWithData(userRanks);
  }

  /**
   * 刷新用户的基本信息
   * @return 用户详情,包含AC信息
   */
  @GetMapping(value = "/user/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO userDetail(@PathVariable("id") Long id,HttpServletResponse response){

    ResultVO<UserInfoVO> resultVO = new ResultVO<>();
    UserDO loginUser = userService.getCurrent();
    if (Objects.isNull(loginUser)) {
      return resultVO.buildWithMsgAndStatus(PosCode.NO_LOGIN, "用户未登录");
    }
    //获取用户AC信息
    List<Integer> problemIds = solutionService.queryACProblems(loginUser.getId());
    //获取用户排名信息
    Long rank = redisTemplate.opsForZSet().reverseRank(RedisKey.RANK_USER,id.toString());
    UserInfoVO userInfoVO = UserInfoVO.assemble(UserConvert.user2UserDTO(loginUser), problemIds, rank);
    //写到cookie中
    WebUtils.addCookie(response,"currentUser", JSON.toJSONString(userInfoVO),-1,null, null,false);
    return resultVO.buildOKWithData(userInfoVO);
  }

  /**
   * 获取用户排名
   * 当前用户少,可以做到实时查询
   */
  @GetMapping(value = "/rank/users",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO rankUsers(){
    ResultVO<List<UserRankVO>> resultVO = new ResultVO<>();
    List<UserDTO> users = userService.queryForRank();
    return resultVO.buildOKWithData(UserRankVO.assemble(users));
  }

  /**
   * 更新用户信息
   */
  @PostMapping(value = "/user/update",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO updateUser(@RequestParam("nickname") String nickName, Long id, String avatar,
      String lang,String intro,String blog){
    ResultVO resultVO = new ResultVO();
    //登录检查
    UserDO loginUser = userService.getCurrent();
    if (Objects.isNull(loginUser)) {
      return resultVO.buildWithMsgAndStatus(PosCode.NO_LOGIN, "用户未登录");
    }
    if (Objects.isNull(id) || !Objects.equals(loginUser.getId(), id)) {
      return resultVO.buildWithMsgAndStatus(PosCode.NO_PRIVILEGE, "用户未登录");
    }
    //更新信息检查
    if (StringUtils.isNotEmpty(nickName)) {
      loginUser.setNickname(nickName);
    }else {
      return resultVO.buildWithMsgAndStatus(PosCode.PARAM_ERROR, "用户昵称不能为空");
    }
    if (StringUtils.isNotEmpty(avatar)) {
      loginUser.setAvatar(avatar);
    }
    if (StringUtils.isNotEmpty(lang)) {
      //浏览器端传加号会出问题
      if (StringUtils.equals("C2",lang)){
        lang = "C++";
      }
      LanguageUtil.Language language = LanguageUtil.getLanguage(lang);
      if (Objects.isNull(language)) {
        return resultVO.buildWithMsgAndStatus(PosCode.PARAM_ERROR, "所选语言不存在");
      }
      loginUser.setLanguage(lang);
    }
    if (Objects.nonNull(intro)) {
      loginUser.setIntro(intro);
    }
    if (Objects.nonNull(blog)) {
      loginUser.setBlog(blog);
    }
    //更新到数据库
    boolean result = userService.updateUserSelective(loginUser);
    if (!result) {
      return resultVO.buildWithMsgAndStatus(PosCode.INTER_ERROR, "更新失败");
    }
    return resultVO.buildOK();
  }


  /**
   * 更改密码
   * @param passwd 新密码
   * @param code 验证码
   */
  @PostMapping(value = "/user/changePwd")
  public ResultVO changePasswd(String passwd,String code) {
    ResultVO resultVO = new ResultVO();
    //登录检查
    UserDO loginUser = userService.getCurrent();
    if (Objects.isNull(loginUser)) {
      return resultVO.buildWithMsgAndStatus(PosCode.NO_LOGIN, "用户未登录");
    }
    //验证码检查
    String key = LoginController.CODE_PREFIX+ CodeType.FIND_PWD.getValue()+loginUser.getEmail();
    String value = redisTemplate.opsForValue().get(key);
    if (!StringUtils.equals(code, value)) {
      return resultVO.buildWithMsgAndStatus(PosCode.PARAM_ERROR, "验证码错误");
    }
    //更新密码
    if (StringUtils.isEmpty(passwd)) {
      return resultVO.buildWithMsgAndStatus(PosCode.PARAM_ERROR, "参数错误");
    }
    if (passwd.length() < 6 || passwd.length()>30) {
      return resultVO.buildWithMsgAndStatus(PosCode.PARAM_ERROR, "密码长度超出范围");
    }
    loginUser.setPassword(DigestUtils.sha256Hex(passwd));
    //更新到数据库
    boolean result = userService.updateUserSelective(loginUser);
    if (!result) {
      return resultVO.buildWithMsgAndStatus(PosCode.INTER_ERROR, "更新失败");
    }
    return resultVO.buildOK();
  }

  /**
   * 头像上传
   */
  @PostMapping(value = "/user/upload/avatar",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO uploadAvatar(@RequestParam(value = "avatar",required = true) MultipartFile avatar){
    ResultVO<JSONObject> resultVO = new ResultVO<>();
    //登录检查
    UserDO loginUser = userService.getCurrent();
    if (Objects.isNull(loginUser)) {
      return resultVO.buildWithMsgAndStatus(PosCode.NO_LOGIN, "用户未登录");
    }
    //文件检查
    if (!imageSuffixs.keySet().contains(avatar.getContentType())) {
      return resultVO.buildWithMsgAndStatus(PosCode.PARAM_ERROR, "不支持的图片类型");
    }
    String fileName = DigestUtils.sha1Hex(avatar.getOriginalFilename())+imageSuffixs.get(avatar
        .getContentType());
    try {
      if (!avatar.isEmpty() && avatar.getSize() < 1048576) {
        storagePlugin.upload(fileName,avatar.getInputStream());
      }else {
        return resultVO.buildWithMsgAndStatus(PosCode.PARAM_ERROR, "文件大小超出限制");
      }
    } catch (IOException e) {
      log.error("uploadAvatar error",e);
      return resultVO.buildWithMsgAndStatus(PosCode.INTER_ERROR, "上传出错,请稍后再试");
    }
    //返回
    JSONObject result = new JSONObject();
    result.put("url", storagePlugin.getUrl(fileName));
    return resultVO.buildOKWithData(result);
  }
}
