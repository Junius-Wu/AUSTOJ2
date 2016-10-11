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

    @RequestMapping(value = "/practice")
    public String practice(){
        return "practice";
    }

    @RequestMapping(value = "/master")
    public String master(){
        return "master";
    }

    @RequestMapping(value = "/contest")
    public String contest(){
        return "contest";
    }

    @RequestMapping(value = "/rank")
    public String rank(){
        return "rank";
    }


}
