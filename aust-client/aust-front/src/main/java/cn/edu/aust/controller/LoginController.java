package cn.edu.aust.controller;

import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.aust.exception.PageException;
import cn.edu.aust.service.UserService;

/**
 * 登录控制器
 * @author Niu Li
 * @date 2016/9/11
 */
@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    /**
     * 前往登录页面
     */
    @GetMapping(value = "login",produces = "text/html;charset=UTF-8")
    public String login(HttpServletRequest request) {
        //获取进入前的链接
        Optional.ofNullable(request.getHeader("referer"))
                .ifPresent(s -> request.getSession().setAttribute("referer", s));
        return "login";
    }

    /**
     * 用户登录控制
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public @ResponseBody
    JSONObject login(String username, String password, String codevalidate,
                     HttpSession session, HttpServletRequest request, HttpServletResponse response) throws PageException {
        JSONObject result = new JSONObject();
//
//        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
//            return ReturnUtil.packingRes(result, ResultVo.LOGIN_ERROR);
//        }
//        //验证码验证
//        String code = (String) session.getAttribute("codeValidate");
//        if (!StringUtils.equalsIgnoreCase(code, codevalidate)) {
//            return ReturnUtil.packingRes(result, ResultVo.CODE_ERROR);
//        }
//        User user;
//        Setting setting = SystemUtil.getSetting();
//        if (username.contains("@") && setting.isIsEmailLogin()){
//            user = userService.selectByEmail(username);
//        }else {
//            user = userService.selectByUsername(username);
//        }
//        //验证不存在
//        if (user == null) {
//            return ReturnUtil.packingRes(result, ResultVo.LOGIN_ERROR);
//        }
//        //验证是否冻结
//        if (user.getDefunct()){
//            return ReturnUtil.packingRes(result,ResultVo.USER_DEFUNCT);
//        }
//        //验证锁定状态
//        if (user.getIslock()){
//            int accountLockTime = setting.getAccountLockTime();
//            //锁定时间0,则永久锁定
//            if (accountLockTime == 0){
//                return ReturnUtil.packingRes(result,ResultVo.USER_LOCKED);
//            }
//            Date lockdate = user.getLockdate();
//            Date unlockdate = DateUtils.addMinutes(lockdate,accountLockTime);
//            if (new Date().after(unlockdate)){
//                user.setIslock(false);
//                user.setLoginfail(0);
//                user.setLockdate(null);
//                userService.updateByPrimaryKey(user);
//            }else {
//                return ReturnUtil.packingRes(result,ResultVo.USER_LOCKED);
//            }
//        }
//        //验证密码
//        if(!user.getPassword().equals(DecriptUtil.SHA1(password.trim()))){
//            int accountLockCount = user.getLoginfail()+1;
//
//            LogUtil.infoIf(logger,
//                    ()->accountLockCount > setting.getAccountLockCount(),//条件
//                    ()->{//执行
//                        user.setLockdate(new Date());
//                        user.setIslock(true);
//                    },
//                    ()->"用户:"+user.getUsername()+" 已被锁定");//日志
//
//            user.setLoginfail(accountLockCount);
//            userService.updateByPrimaryKeySelective(user);
//            if (user.getIslock()){
//                return ReturnUtil.packingRes(result,ResultVo.USER_LOCKED);
//            }else if (accountLockCount <= 2){
//                return ReturnUtil.packingRes(result,ResultVo.LOGIN_ERROR);
//            }else {
//                return ReturnUtil.packingRes(result,ResultVo.LOGIN_ERROR,
//                        "密码错误,若再错误"+(setting.getAccountLockCount()-accountLockCount+1)+"次,则锁定账户");
//            }
//        }
//        //登录成功
//        user.setIp(WEBUtil.getIp(request));
//        user.setModifydate(new Date());
//        user.setLoginfail(0);
//        userService.updateByPrimaryKeySelective(user);
//        //登录成功加入session
//        session = request.getSession();
//        session.setAttribute(User.PRINCIPAL_ATTRIBUTE_NAME,new Principal(user));
//        LogUtil.info(logger,()->user.getUsername() + "已登录");
//
//        WEBUtil.addCookie(request, response, User.USERNAME_COOKIE_NAME, user.getUsername()
//                ,null,setting.getCookiePath(),setting.getCookieDomain(),null);
//
//        if (StringUtils.isNotEmpty(user.getNickname())) {
//            WEBUtil.addCookie(request, response, User.USERNAME_COOKIE_NAME, user.getNickname()
//                    ,null,setting.getCookiePath(),setting.getCookieDomain(),null);
//        }
//        //跳转到之前的页面
//        Optional<String> redirect = Optional.ofNullable((String) session.getAttribute("referer"));
//        result.put("referer",redirect.orElse("/index"));
//
//        session.removeAttribute("referer");
        return null;
    }

    /**
     * 退出方法
     * @return 返回首页
     */
    @RequestMapping(value = "/loginout",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String loginOut(HttpServletRequest request,HttpServletResponse response){
//        Setting setting = SystemUtil.getSetting();
//        WEBUtil.removeCookie(request, response, User.USERNAME_COOKIE_NAME,setting.getCookiePath(),setting.getCookieDomain());
//        WEBUtil.removeCookie(request, response, User.NICKNAME_COOKIE_NAME,setting.getCookiePath(),setting.getCookieDomain());
//        request.getSession().invalidate();
        return "redirect:/";
    }
}
