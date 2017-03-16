package cn.edu.aust.controller;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.aust.common.constant.PosCode;
import cn.edu.aust.common.entity.ResultPackag;
import cn.edu.aust.common.entity.Setting;
import cn.edu.aust.common.service.JedisClient;
import cn.edu.aust.common.util.SystemUtil;
import cn.edu.aust.common.util.WebUtils;
import cn.edu.aust.dto.UserDTO;
import cn.edu.aust.exception.PageException;
import cn.edu.aust.pojo.entity.UserDO;
import cn.edu.aust.service.UserService;

/**
 * 登录控制器
 * @author Niu Li
 * @date 2016/9/11
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private JedisClient jedisClient;
    /**
     * 前往登录页面
     */
    @GetMapping(produces = "text/html;charset=UTF-8")
    public String login(HttpServletRequest request) {
        //获取进入前的链接
        Optional.ofNullable(request.getHeader("referer"))
                .ifPresent(s -> request.getSession().setAttribute("referer", s));
        return "login";
    }

    /**
     * 用户登录控制
     */
    @PostMapping(produces = "application/json; charset=UTF-8")
    public @ResponseBody
    ResultPackag<?> login(String email, String password, String codevalidate,
                 HttpSession session, HttpServletRequest request, HttpServletResponse response) throws PageException {
        JSONObject result = new JSONObject();
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)){
            return new ResultPackag<>(20001,"用户民或密码不能为空");
        }
        //验证码验证
        String code = (String) session.getAttribute("codeValidate");
        if (!StringUtils.equalsIgnoreCase(code, codevalidate)) {
            return new ResultPackag<>(PosCode.CODE_ERROR);
        }
        UserDO userDO = new UserDO();
        Setting setting = SystemUtil.getSetting(jedisClient);
        userDO.setEmail(email);
        userDO = userService.queryOne(userDO);
        //验证不存在
        if (userDO == null) {
            return new ResultPackag<>(PosCode.NO_REGISTER);
        }
        //验证是否冻结
        if (userDO.getIsDefunct() == 1){
            return new ResultPackag<>(PosCode.USER_FREEZE);
        }
        //验证锁定状态
        if (userDO.getIsLock() == 1){
            int accountLockTime = setting.getAccountLockTime();
            //锁定时间0,则永久锁定
            if (accountLockTime == 0){
                return new ResultPackag<>(PosCode.USER_LOCKED);
            }
            Date lockdate = userDO.getLockdate();
            Date unlockdate = DateUtils.addMinutes(lockdate,accountLockTime);
            if (new Date().after(unlockdate)){
                userDO.setIsLock((byte) 0);
                userDO.setLoginfail(0);
                userDO.setLockdate(null);
                userService.updateSelective(userDO);
            }else {
                return new ResultPackag<>(PosCode.USER_LOCKED);
            }
        }
        //验证密码
        if(!userDO.getPassword().equals(DigestUtils.sha256Hex(password.trim()))){
            int accountLockCount = userDO.getLoginfail()+1;
            if (accountLockCount > setting.getAccountLockCount()){
                userDO.setLockdate(new Date());
                userDO.setIsLock((byte) 1);
                logger.info("用户:{}已被锁定", userDO.getEmail());
            }
            userDO.setLoginfail(accountLockCount);
            userService.updateSelective(userDO);
            if (userDO.getIsLock() == 1){
                return new ResultPackag<>(PosCode.USER_LOCKED);
            }else {
                return new ResultPackag<>(PosCode.USER_LOCKED.getStatus(),
                        "密码错误,若再错误"+(setting.getAccountLockCount()-accountLockCount+1)+"次,则锁定账户");
            }
        }
        //登录成功
        userDO.setIp(WebUtils.getIp(request));
        userDO.setModifydate(new Date());
        userDO.setLoginfail(0);
        userService.updateSelective(userDO);
        //登录成功加入session
        session = request.getSession();
        session.setAttribute(UserDTO.PRINCIPAL_ATTRIBUTE_NAME,new UserDTO(userDO));
        logger.info("用户:{}已登录", userDO.getEmail());

        WebUtils.addCookie(response, UserDTO.NICKNAME_COOKIE_NAME, userDO.getNickname()
                    ,null,setting.getCookiePath(),setting.getCookieDomain(),null);
        //跳转到之前的页面
        Optional<String> redirect = Optional.ofNullable((String) session.getAttribute("referer"));
        result.put("referer",redirect.orElse("/index"));

        session.removeAttribute("referer");
        return new ResultPackag<>(PosCode.OK,result);
    }

    /**
     * 退出方法
     * @return 返回首页
     */
    @GetMapping(value = "/out",produces = "text/html;charset=UTF-8")
    public String loginOut(HttpServletRequest request,HttpServletResponse response){
        Setting setting = SystemUtil.getSetting(jedisClient);
        WebUtils.removeCookie(response, UserDTO.USERNAME_COOKIE_NAME,setting.getCookiePath(),setting.getCookieDomain());
        WebUtils.removeCookie(response, UserDTO.NICKNAME_COOKIE_NAME,setting.getCookiePath(),setting.getCookieDomain());
        request.getSession().invalidate();
        return "redirect:/";
    }
}
