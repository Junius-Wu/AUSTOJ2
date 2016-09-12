package cn.edu.aust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

import cn.edu.aust.mapper.UserMapper;

/**
 * @author Niu Li
 * @date 2016/9/2
 */
@Controller
public class IndexController {

    @Resource
    private UserMapper userMapper;

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }
}
