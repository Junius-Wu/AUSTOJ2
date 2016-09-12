package cn.edu.aust.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.edu.aust.entity.User;
import cn.edu.aust.exception.PageException;
import cn.edu.aust.service.UserService;
import cn.edu.aust.util.DecriptUtil;

/**
 * @author Niu Li
 * @date 2016/9/11
 */
@Controller
public class LoginController {

    @Resource(name = "userServiceImpl")
    private UserService userService;
    /**
     * 前往登录页面
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(HttpServletRequest request){
        //获取进入前的链接
        String referer = request.getHeader("referer");
        if (!StringUtils.isEmpty(referer)){
            request.getSession().setAttribute("referer",referer);
        }
        return "login";
    }

    /**
     * 用户登录控制
     * @param user
     * @param attr    重定向传参数
     * @param session
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, BindingResult br, String codevalidate, String rmb_me,
                        RedirectAttributes attr, HttpSession session) throws PageException {
        //校验主要在客户端,所以服务端只需要简单判断
        if (br.hasErrors()) {
            throw new PageException("请误尝试非法登录");
        }
        //验证码验证
        String code = (String) session.getAttribute("codeValidate");
        if (!StringUtils.equalsIgnoreCase(code, codevalidate)) {
            attr.addFlashAttribute("error", "验证码错误");
            return "redirect:/login";
        }
        //验证账户
        user = userService.selectByUsername(user.getUsername());
        if (user == null || !user.getPassword().equals(DecriptUtil.SHA1(user.getPassword().trim())) ) {
            attr.addFlashAttribute("error", "用户名或密码错误");
            return "redirect:/login";
        }
        //登录成功加入session
        session.setAttribute("userLogin", user);
        logger.info(user.getUsername()+"已登录");
        //cookies操作保留
        //跳转到之前的页面
        String redirect = (String) session.getAttribute("referer");
        return StringUtils.isEmpty(redirect)?("redirect:/user/" + user.getId()):("redirect:" + redirect);
    }

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
}
