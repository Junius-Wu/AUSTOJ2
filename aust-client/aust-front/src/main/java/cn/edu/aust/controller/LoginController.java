package cn.edu.aust.controller;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.aust.common.constant.PosCode;
import cn.edu.aust.common.entity.ResultVO;
import cn.edu.aust.common.util.WebUtils;
import cn.edu.aust.dto.UserDTO;
import cn.edu.aust.exception.PageException;
import cn.edu.aust.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * 登录控制器
 *
 * @author Niu Li
 * @date 2016/9/11
 */
@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {
  /**
   * 认证名称
   */
  private static final String PRINCIPAL_ATTRIBUTE_NAME = "userinfo";

  @Resource
  private UserService userService;

  /**
   * 用户登录控制
   */
  @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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

}
