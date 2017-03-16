package cn.edu.aust.controller;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import cn.edu.aust.pojo.entity.UserDO;
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
    public String toRegister(String email, HttpServletRequest request, Model model){
        //获取进入前的链接
        Optional.ofNullable(request.getHeader("referer"))
                .ifPresent(s -> request.getSession().setAttribute("referer", s));
        model.addAttribute("email",email);
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
            return new Result<PosCode>(PosCode.CODE_ERROR);
        }
        Setting setting = SystemUtil.getSetting(jedisClient);
        if (!setting.isIsRegisterEnabled()){
            return new Result<PosCode>(PosCode.NOOPEN_REGISTER);
        }
        //参数校验
        Matcher matcher = emailPattern.matcher(email);
        if (!matcher.matches()){
            return new Result<PosCode>(PosCode.USERNAME_NOALLOW);
        }
        if (StringUtils.isEmpty(password) || password.length() > 30){
            return new Result<PosCode>(PosCode.PASSWORD_NOALLOW);
        }
        if (StringUtils.isEmpty(nickname) || nickname.length() > 30){
            return new Result<PosCode>(PosCode.NICKNAME_NOALLOW);
        }
        //用户存在验证
        if (userService.judgeUsernameOrEmail(null,email)){
            return new Result<PosCode>(PosCode.USERNAME_EXIST);
        }
        //注册用户
        UserDO userDO = new UserDO();
        userDO.setPassword(DigestUtils.sha256Hex(password));
        userDO.setCreatedate(new Date());
        userDO.setModifydate(userDO.getCreatedate());
        userDO.setIp(WebUtils.getIp(request));
        userDO.setNickname(nickname);
        userDO.setEmail(email);
        userDO.setUsername(email);
        userDO.setIsDefunct((byte) 2);//设置待验证状态
        userService.save(userDO);

        //登录成功加入session
        session = request.getSession();
        session.setAttribute(UserDTO.PRINCIPAL_ATTRIBUTE_NAME,new UserDTO(userDO));
        logger.info("{}用户已注册", userDO.getEmail());

        //存储cookies
        WebUtils.addCookie(response,UserDTO.NICKNAME_COOKIE_NAME, userDO.getNickname(),
                    null,setting.getCookiePath(),setting.getCookieDomain(),null);
        //发送邮件,验证
        mailService.sendRegister(email,jedisClient);
        //跳转到之前的页面
        Optional<String> redirect = Optional.ofNullable((String) session.getAttribute("referer"));
        JSONObject result = new JSONObject();
        result.put("referer",redirect.orElse("/index"));

        //移除session
        session.removeAttribute("referer");
        return new Result<JSONObject>(PosCode.OK,result);
    }

    /**
     * 检查邮箱是否存在(目前屏蔽用户名注册)
     * @param username 要检查的用户名
     */
    @GetMapping(value = "/check",produces = "application/json; charset=UTF-8")
    public @ResponseBody Result<PosCode> checkUsername(String username,String email){

        boolean check = userService.judgeUsernameOrEmail(null,email);
        if (check){
            return new Result<>(PosCode.USERNAME_EXIST);
        }
        //合法性检查
        Matcher matcher = emailPattern.matcher(email);
        if (!matcher.matches()){
            return new Result<>(PosCode.USERNAME_NOALLOW);
        }
        //是否含有违规字段
//        if (userService.usernameIsDisabled(username)){
//            return new Result<>(true,PosCode.USERNAME_NOALLOW);
//        }
        return new Result<>(PosCode.OK);
    }

    /**
     * 验证邮箱token
     * @param token token
     */
    @GetMapping(value = "/check/token",produces = "application/json; charset=UTF-8")
    public @ResponseBody Result<PosCode> checkToken(String token){
        String email = jedisClient.get(token);
        if (StringUtils.isEmpty(email)){
            return new Result<>(PosCode.URL_ERROR);
        }
        //判断用户状态
        UserDO userDO = new UserDO();
        userDO.setEmail(email);
        userDO = userService.queryOne(userDO);
        if (userDO.getId() == null || userDO.getIsDefunct() != 2){
            return new Result<>(PosCode.ALREADY_REGISTER);
        }
        //更新用户 已验证
        userDO.setIsDefunct((byte) 0);
        userDO.setModifydate(new Date());
        userService.update(userDO);
        //清除缓存
        jedisClient.del(token);
        return new Result<>(PosCode.OK);
    }
}
