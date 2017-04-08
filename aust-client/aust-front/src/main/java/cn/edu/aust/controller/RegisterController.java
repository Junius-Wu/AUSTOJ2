package cn.edu.aust.controller;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.aust.common.constant.PosCode;
import cn.edu.aust.common.entity.ResultVO;
import cn.edu.aust.common.entity.Setting;
import cn.edu.aust.common.util.WebUtils;
import cn.edu.aust.service.SettingService;
import cn.edu.aust.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户注册控制器
 *
 * @author Niu Li
 * @date 2016/9/17
 */
@Controller
@RequestMapping("register")
@Slf4j
public class RegisterController {

  @Resource
  private UserService userService;
  @Resource
  private SettingService settingService;
  /**
   * 邮箱匹配正则
   */
  private Pattern emailPattern = Pattern.compile("^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$");

  /**
   * 注册方法
   */
  @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public
  @ResponseBody
  ResultVO<?> register(String email, String password, String nickname, String codevalidate,
      HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
    Setting setting = settingService.getSetting();
    ResultVO<JSONObject> resultVO = new ResultVO<>();
    //验证码验证
    String code = (String) session.getAttribute("codeValidate");
    if (!StringUtils.equalsIgnoreCase(code, codevalidate)) {
      return resultVO.buildWithPosCode(PosCode.CODE_ERROR);
    }
    if (!setting.getIsRegisterEnabled()) {
      return resultVO.buildWithPosCode(PosCode.NOOPEN_REGISTER);
    }
    //参数校验
    Matcher matcher = emailPattern.matcher(email);
    if (!matcher.matches()) {
      return resultVO.buildWithPosCode(PosCode.USERNAME_NOALLOW);
    }
    if (StringUtils.isEmpty(password) || password.length() > 30) {
      return resultVO.buildWithPosCode(PosCode.PASSWORD_NOALLOW);
    }
    if (StringUtils.isEmpty(nickname) || nickname.length() > 30) {
      return resultVO.buildWithPosCode(PosCode.NICKNAME_NOALLOW);
    }
    //用户存在验证
    if (userService.judgeUsernameOrEmail(null, email)) {
      return resultVO.buildWithPosCode(PosCode.USERNAME_EXIST);
    }
    //注册用户
    userService.registerUser(password,WebUtils.getIp(request),nickname,email);
    log.info("{}用户已注册", email);

    //跳转到之前的页面
    return resultVO.buildOK();
  }

  /**
   * 检查邮箱是否存在(目前屏蔽用户名注册)
   *
   * @param username 要检查的用户名
   */
  @GetMapping(value = "/check", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public
  @ResponseBody
  ResultVO checkUsername(String username, String email) {
    ResultVO<PosCode> resultVO = new ResultVO<>();
    boolean check = userService.judgeUsernameOrEmail(null, email);
    if (check) {
      return resultVO.buildWithPosCode(PosCode.USERNAME_EXIST);
    }
    //合法性检查
    Matcher matcher = emailPattern.matcher(email);
    if (!matcher.matches()) {
      return resultVO.buildWithPosCode(PosCode.USERNAME_NOALLOW);
    }
    //是否含有违规字段
    if (userService.usernameIsDisabled(username)){
        return resultVO.buildWithPosCode(PosCode.USERNAME_NOALLOW);
    }
    return resultVO.buildOK();
  }

  /**
   * 验证邮箱token
   *
   * @param token token
   */
  @GetMapping(value = "/check/token", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody ResultVO checkToken(String token) {
    ResultVO resultVO = new ResultVO<>();
    if (!userService.checkEmailToken(token,resultVO)){
      return resultVO;
    }
    return resultVO.buildOK();
  }
}
