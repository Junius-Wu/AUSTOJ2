package cn.edu.aust.controller;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.aust.common.constant.PosCode;
import cn.edu.aust.common.entity.Result;
import cn.edu.aust.common.entity.Setting;
import cn.edu.aust.common.service.JedisClient;
import cn.edu.aust.common.util.SystemUtil;
import cn.edu.aust.common.util.WebUtils;
import cn.edu.aust.dto.UserDTO;
import cn.edu.aust.pojo.entity.User;
import cn.edu.aust.service.MailService;
import cn.edu.aust.service.UserService;

/**
 * 用户注册控制器
 * @author Niu Li
 * @date 2016/9/17
 */
@Controller
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private UserService userService;
    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private MailService mailService;
    /**
     * 邮箱匹配正则
     */
    private Pattern emailPattern = Pattern.compile("^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$");


    private static Logger logger = LoggerFactory.getLogger(RegisterController.class);
    /**
     * 前往注册页面
     *
     * 该页面获取进入前的页面,注册完毕后跳转
     */
    @GetMapping(produces = "text/html;charset=UTF-8")
    public String toRegister(HttpServletRequest request){
        //获取进入前的链接
        Optional.ofNullable(request.getHeader("referer"))
                .ifPresent(s -> request.getSession().setAttribute("referer", s));
        return "register";
    }

    /**
     * 注册方法
     */
    @PostMapping(produces = "application/json; charset=UTF-8")
    public @ResponseBody
    Result<?> register(String email,String password,String nickname, String codevalidate,
                        HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //验证码验证
        String code = (String) session.getAttribute("codeValidate");
        if (!StringUtils.equalsIgnoreCase(code, codevalidate)) {
            return new Result<PosCode>(false,PosCode.CODE_ERROR.getMsg());
        }
        Setting setting = SystemUtil.getSetting(jedisClient);
        if (!setting.isIsRegisterEnabled()){
            return new Result<PosCode>(false,PosCode.NO_REGISTER.getMsg());
        }
        //参数校验
        Matcher matcher = emailPattern.matcher(email);
        if (!matcher.matches()){
            return new Result<PosCode>(false,PosCode.USERNAME_NOALLOW.getMsg());
        }
        if (StringUtils.isEmpty(password) || password.length() > 30){
            return new Result<PosCode>(false,PosCode.PASSWORD_NOALLOW.getMsg());
        }
        if (StringUtils.isEmpty(nickname) || nickname.length() > 30){
            return new Result<PosCode>(false,PosCode.NICKNAME_NOALLOW.getMsg());
        }
        //用户存在验证
        if (userService.judgeUsernameOrEmail(null,email)){
            return new Result<PosCode>(false,PosCode.USERNAME_EXIST.getMsg());
        }
        //注册用户
        User user = new User();
        user.setPassword(DigestUtils.sha256Hex(password));
        user.setCreatedate(new Date());
        user.setModifydate(user.getCreatedate());
        user.setIp(WebUtils.getIp(request));
        user.setNickname(nickname);
        user.setEmail(email);
        user.setUsername(email);
        user.setIsDefunct((byte) 2);//设置待验证状态
        userService.save(user);

        //登录成功加入session
        session = request.getSession();
        session.setAttribute(UserDTO.PRINCIPAL_ATTRIBUTE_NAME,new UserDTO(user));
        logger.info("{}用户已注册",user.getEmail());

        //存储cookies
        WebUtils.addCookie(response, UserDTO.USERNAME_COOKIE_NAME, user.getUsername(),
                null,setting.getCookiePath(),setting.getCookieDomain(),null);
        if (StringUtils.isNotEmpty(user.getNickname())) {
            WebUtils.addCookie(response,UserDTO.NICKNAME_COOKIE_NAME, user.getNickname(),
                    null,setting.getCookiePath(),setting.getCookieDomain(),null);
        }
        //发送邮件,验证
        mailService.sendRegister(email,jedisClient);
        //跳转到之前的页面
        Optional<String> redirect = Optional.ofNullable((String) session.getAttribute("referer"));
        JSONObject result = new JSONObject();
        result.put("referer",redirect.orElse("/index"));

        //移除session
        session.removeAttribute("referer");
        return new Result<JSONObject>(true,result);
    }

    /**
     * 检查邮箱是否存在(目前屏蔽用户名注册)
     * @param username 要检查的用户名
     */
    @GetMapping(value = "/check",produces = "application/json; charset=UTF-8")
    public @ResponseBody Result<PosCode> checkUsername(String username,String email){

        boolean check = userService.judgeUsernameOrEmail(null,email);
        if (check){
            return new Result<>(false,PosCode.USERNAME_EXIST.getMsg());
        }
        //合法性检查
        Matcher matcher = emailPattern.matcher(email);
        if (!matcher.matches()){
            return new Result<>(false,PosCode.USERNAME_NOALLOW.getMsg());
        }
        //是否含有违规字段
//        if (userService.usernameIsDisabled(username)){
//            return new Result<>(true,PosCode.USERNAME_NOALLOW);
//        }
        return new Result<>(true,PosCode.OK);
    }

    /**
     * 验证邮箱token
     * @param token token
     */
    @GetMapping(value = "/check/token",produces = "application/json; charset=UTF-8")
    public @ResponseBody Result<PosCode> checkToken(String token){
        String email = jedisClient.get(token);
        if (StringUtils.isEmpty(email)){
            return new Result<>(true,PosCode.URL_ERROR.getMsg());
        }
        //判断用户状态
        User user = new User();
        user.setEmail(email);
        user = userService.queryOne(user);
        if (user.getId() == null || user.getIsDefunct() != 2){
            return new Result<>(false,PosCode.ALREADY_REGISTER.getMsg());
        }
        //更新用户 已验证
        user.setIsDefunct((byte) 0);
        user.setModifydate(new Date());
        userService.update(user);
        //清除缓存
        jedisClient.del(token);
        return new Result<>(true,PosCode.OK);
    }
}
