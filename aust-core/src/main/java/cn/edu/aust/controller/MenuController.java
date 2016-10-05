package cn.edu.aust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Niu Li
 * @date 2016/9/2
 */
@Controller
public class MenuController {

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }
    @RequestMapping(value = "/start")
    public String start(){
        return "start";
    }


}
