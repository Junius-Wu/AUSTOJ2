package cn.edu.aust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.annotation.Resource;

import cn.edu.aust.entity.User;
import cn.edu.aust.service.UserService;

/**
 * @author Niu Li
 * @date 2016/9/2
 */
@Controller
public class IndexController {

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public @ResponseBody List<User> test(){
        List<User> users = userService.selectAll();
        return users;
    }
}
