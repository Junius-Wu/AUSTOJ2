package cn.edu.aust.controller;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import cn.edu.aust.Principal;
import cn.edu.aust.ResultVo;
import cn.edu.aust.Setting;
import cn.edu.aust.entity.User;
import cn.edu.aust.service.UserService;
import cn.edu.aust.util.CheckParamUtil;
import cn.edu.aust.util.DecriptUtil;
import cn.edu.aust.util.LoggerUtil;
import cn.edu.aust.util.SystemUtil;
import cn.edu.aust.util.WEBUtil;

/**
 * @author Niu Li
 * @date 2016/9/17
 */
@Controller
public class RegisterController {
    @Resource(name = "userServiceImpl")
    private UserService userService;

    private static Logger logger = LoggerFactory.getLogger(RegisterController.class);
    /**
     * 前往注册页面
     */
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String toRegister(HttpServletRequest request){
        //获取进入前的链接
        String referer = request.getHeader("referer");
        if (!StringUtils.isEmpty(referer)) {
            request.getSession().setAttribute("referer", referer);
        }
        return "register";
    }

    /**
     * 注册方法
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public @ResponseBody JSONObject register(@Valid User user, BindingResult br, String codevalidate,
                                             HttpServletRequest request, HttpServletResponse response){
        JSONObject result = new JSONObject();
        HttpSession session = request.getSession();
        //验证码验证
        String code = (String) session.getAttribute("codeValidate");
        if (!StringUtils.equalsIgnoreCase(code, codevalidate)) {
            return CheckParamUtil.packingRes(result,ResultVo.CODE_ERROR);
        }
        Setting setting = SystemUtil.getSetting();
        if (!setting.isIsRegisterEnabled()){
            return CheckParamUtil.packingRes(result,ResultVo.REGISTER_ENABLE);
        }
        //参数格式验证
        if (br.hasErrors()){
            result.put("status",ResultVo.REGISTER_ERROR.getStatus());
            result.put("msg",br.getFieldErrors().get(0).getDefaultMessage());
            return result;
        }
        if (userService.usernameIsDisabled(user.getUsername())){
            return CheckParamUtil.packingRes(result,ResultVo.USERNAME_ENABLE);
        }
        //用户存在验证
        if (userService.selectByUsername(user.getUsername()) != null
                || userService.selectByEmail(user.getEmail()) != null){
            return CheckParamUtil.packingRes(result,ResultVo.USERNAME_EXIST);
        }
        //注册用户
        user.setPassword(DecriptUtil.SHA1(user.getPassword()));
        user.setCreatedate(new Date());
        user.setModifydate(user.getCreatedate());
        user.setIp(WEBUtil.getIp(request));
        userService.insertSelective(user);
        //登录成功加入session
        session = request.getSession();
        session.setAttribute(User.PRINCIPAL_ATTRIBUTE_NAME,new Principal(user));
        LoggerUtil.info(logger,()->user.getUsername() + "已注册");

        //存储cookies
        WEBUtil.addCookie(request, response, User.USERNAME_COOKIE_NAME, user.getUsername()
                ,null,setting.getCookiePath(),setting.getCookieDomain(),null);

        if (StringUtils.isNotEmpty(user.getNickname())) {
            WEBUtil.addCookie(request, response, User.USERNAME_COOKIE_NAME, user.getNickname()
                    ,null,setting.getCookiePath(),setting.getCookieDomain(),null);
        }
        //跳转到之前的页面
        String redirect = (String) session.getAttribute("referer");
        if (!StringUtils.isEmpty(redirect)) {
            result.put("referer",redirect);
        }else {
            result.put("referer","/index");
        }
        session.removeAttribute("referer");
        return CheckParamUtil.packingRes(result,ResultVo.OK);
    }

    /**
     * 检查用户名是否存在
     * @param username 要检查的用户名
     */
    @RequestMapping(value = "/check/{username}",method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
    public @ResponseBody JSONObject checkUsername(@PathVariable(value = "username") String username){
        JSONObject result = new JSONObject();
        User user = userService.selectByUsername(username);
        if (user != null){
            return CheckParamUtil.packingRes(result, ResultVo.USERNAME_EXIST);
        }
        if (!username.matches("^[a-zA-Z0-9_]{3,16}$")){
            return CheckParamUtil.packingRes(result,ResultVo.USERNAME_NOALLOW);
        }
        return CheckParamUtil.packingRes(result,ResultVo.OK);
    }
    /**
     * 检查邮箱是否已存在
     * @param email 要检查的email
     */
    @RequestMapping(value = "/checkemail",method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
    public @ResponseBody JSONObject checkEmail(String email){
        JSONObject result = new JSONObject();
        User user = userService.selectByEmail(email);
        if (user != null){
            return CheckParamUtil.packingRes(result, ResultVo.EMAIL_EXIST);
        }
        return CheckParamUtil.packingRes(result,ResultVo.OK);
    }
}
