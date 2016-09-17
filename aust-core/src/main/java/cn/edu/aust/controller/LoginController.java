package cn.edu.aust.controller;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.aust.Principal;
import cn.edu.aust.ResultVo;
import cn.edu.aust.Setting;
import cn.edu.aust.entity.User;
import cn.edu.aust.exception.PageException;
import cn.edu.aust.service.UserService;
import cn.edu.aust.util.CheckParamUtil;
import cn.edu.aust.util.DecriptUtil;
import cn.edu.aust.util.SystemUtil;
import cn.edu.aust.util.WEBUtil;

/**
 * @author Niu Li
 * @date 2016/9/11
 */
@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Resource(name = "userServiceImpl")
    private UserService userService;

    /**
     * 前往登录页面
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        //获取进入前的链接
        String referer = request.getHeader("referer");
        if (!StringUtils.isEmpty(referer)) {
            request.getSession().setAttribute("referer", referer);
        }
        return "login";
    }

    /**
     * 用户登录控制
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public @ResponseBody JSONObject login(String username, String password, String codevalidate, String rmb_me,
                     HttpSession session, HttpServletRequest request, HttpServletResponse response) throws PageException {
        JSONObject result = new JSONObject();

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            CheckParamUtil.packingRes(result, ResultVo.LOGIN_ERROR);
            return result;
        }
        //验证码验证
        String code = (String) session.getAttribute("codeValidate");
        if (!StringUtils.equalsIgnoreCase(code, codevalidate)) {
            CheckParamUtil.packingRes(result, ResultVo.CODE_ERROR);
            return result;
        }
        User user;
        Setting setting = SystemUtil.getSetting();
        if (username.contains("@") && setting.isIsEmailLogin()){
            user = userService.selectByEmail(username);
        }else {
            user = userService.selectByUsername(username);
        }
        //验证不存在
        if (user == null) {
            CheckParamUtil.packingRes(result, ResultVo.LOGIN_ERROR);
            return result;
        }
        //验证是否冻结
        if (user.getDefunct()){
            CheckParamUtil.packingRes(result,ResultVo.USER_DEFUNCT);
            return result;
        }
        //验证锁定状态
        if (user.getIslock()){
            int accountLockTime = setting.getAccountLockTime();
            //锁定时间0,则永久锁定
            if (accountLockTime == 0){
                CheckParamUtil.packingRes(result,ResultVo.USER_LOCKED);
                return result;
            }
            Date lockdate = user.getLockdate();
            Date unlockdate = DateUtils.addMinutes(lockdate,accountLockTime);
            if (new Date().after(unlockdate)){
                user.setIslock(false);
                user.setLoginfail(0);
                user.setLockdate(null);
                userService.updateByPrimaryKey(user);
            }else {
                CheckParamUtil.packingRes(result,ResultVo.USER_LOCKED);
                return result;
            }
        }
        //验证密码
        if(!user.getPassword().equals(DecriptUtil.SHA1(password.trim()))){
            int accountLockCount = user.getLoginfail()+1;
            if (accountLockCount > setting.getAccountLockCount()){
                user.setLockdate(new Date());
                user.setIslock(true);
                logger.info("用户:"+user.getUsername()+"已被锁定");
            }
            user.setLoginfail(accountLockCount);
            userService.updateByPrimaryKeySelective(user);
            if (user.getIslock()){
                CheckParamUtil.packingRes(result,ResultVo.USER_LOCKED);
                return result;
            }else if (accountLockCount <= 2){
                CheckParamUtil.packingRes(result,ResultVo.LOGIN_ERROR);
                return result;
            }else {
                CheckParamUtil.packingRes(result,ResultVo.LOGIN_ERROR,"密码错误,若再错误"+(setting.getAccountLockCount()-accountLockCount+1)+"次,则锁定账户");
                return result;
            }
        }
        //登录成功
        user.setIp(WEBUtil.getIp(request));
        user.setModifydate(new Date());
        user.setLoginfail(0);
        userService.updateByPrimaryKeySelective(user);
        //登录成功加入session
        session.invalidate();
        session = request.getSession();
        session.setAttribute(User.PRINCIPAL_ATTRIBUTE_NAME,new Principal(user));
        logger.info(user.getUsername() + "已登录");

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
        CheckParamUtil.packingRes(result,ResultVo.OK);
        return result;
    }
}
