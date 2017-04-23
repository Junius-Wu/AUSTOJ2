package cn.edu.aust.controller;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.aust.common.constant.CodeType;
import cn.edu.aust.common.constant.PosCode;
import cn.edu.aust.common.entity.ResultVO;
import cn.edu.aust.common.util.WebUtils;
import cn.edu.aust.dto.UserDTO;
import cn.edu.aust.exception.PageException;
import cn.edu.aust.pojo.entity.UserDO;
import cn.edu.aust.service.MailService;
import cn.edu.aust.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * 登录控制器
 *
 * @author Niu Li
 * @date 2016/9/11
 */
@RestController
@Slf4j
public class LoginController {
  /**
   * 认证名称
   */
  private static final String PRINCIPAL_ATTRIBUTE_NAME = "userinfo";

  @Resource
  private UserService userService;
  @Resource
  private StringRedisTemplate redisTemplate;
  @Resource
  private MailService mailService;
  /**
   * 验证码前缀
   */
  public static final String CODE_PREFIX = "LoginController:sendEmailCode";

  /**
   * 用户登录控制
   */
  @PostMapping(value = "/login",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO<?> login(String email, String password, String codevalidate,
      HttpSession session, HttpServletRequest request, HttpServletResponse response) throws PageException {
    JSONObject result = new JSONObject();
    ResultVO<JSONObject> resultVO = new ResultVO<>();
    if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
       return resultVO.buildWithMsgAndStatus(PosCode.PARAM_ERROR,"用户民或密码不能为空");
    }
    //验证码验证
    String code = (String) session.getAttribute("codeValidate");
    if (!StringUtils.equalsIgnoreCase(code, codevalidate)) {
      return resultVO.buildWithPosCode(PosCode.CODE_ERROR);
    }

    UserDTO userDTO = userService.findByEmail(email);
    //验证不存在
    if (Objects.isNull(userDTO)) {
      return resultVO.buildWithPosCode(PosCode.NO_REGISTER);
    }
    //检查是否能登陆
    if (!userService.checkCanLogin(userDTO,password,WebUtils.getIp(request),resultVO)){
      return resultVO;
    }
    //登录成功加入session,保存cookies
    session = request.getSession();
    session.setAttribute(PRINCIPAL_ATTRIBUTE_NAME, userDTO);
    log.info("用户:{}已登录", email);
    //跳转到之前的页面
    result.put("id", userDTO.getId());
    return resultVO.buildOKWithData(result);
  }

  /**
   * 发送验证码
   */
  @GetMapping(value = "/email/send",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO sendEmailCode(int type,String email){
    ResultVO resultVO = new ResultVO();
    CodeType codeType = CodeType.of(type);
    if (Objects.isNull(codeType)) {
      return resultVO.buildWithMsgAndStatus(PosCode.PARAM_ERROR, "错误的type类型");
    }
    String keyPrefix = CODE_PREFIX +codeType.getValue();
    //判断是否已经发送
    String tempKey = keyPrefix + email;
    String tempValue = redisTemplate.opsForValue().get(tempKey);
    if (tempValue != null && StringUtils.equals("nil", tempValue)) {
      return resultVO.buildWithMsgAndStatus(PosCode.PARAM_ERROR, "操作频繁,请稍后再试");
    }
    try {
      //找回密码,需要验证登录
      if (codeType.getValue() == CodeType.FIND_PWD.getValue()) {
        UserDO loginUser = userService.getCurrent();
        if (Objects.isNull(loginUser)) {
          return resultVO.buildWithMsgAndStatus(PosCode.NO_LOGIN, "用户未登录");
        }
        String key = keyPrefix + loginUser.getEmail();
        mailService.sendCode(key,loginUser.getEmail());
        return resultVO.buildOK();
      }
    } catch (Exception e) {
      log.error("sendEmailCode findpwd error",e);
      return resultVO.buildWithMsgAndStatus(PosCode.INTER_ERROR,"发送失败,内部错误");
    }
    //注册验证邮件
    if (codeType.getValue() == CodeType.REGISTER.getValue()) {
      if (StringUtils.isNotEmpty(email)) {
        mailService.sendRegister(email);
        return resultVO.buildOK();
      }else {
        return resultVO.buildWithMsgAndStatus(PosCode.PARAM_ERROR, "邮箱不能为空");
      }
    }
    return resultVO.buildOK();
  }


}
