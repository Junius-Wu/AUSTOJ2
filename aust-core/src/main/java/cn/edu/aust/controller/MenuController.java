package cn.edu.aust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Niu Li
 * @date 2016/9/2
 */
@Controller
public class MenuController {

    @RequestMapping(value = "/index",produces = "text/html;charset=UTF-8")
    public String index(){
        return "index";
    }
    @RequestMapping(value = "/start",produces = "text/html;charset=UTF-8")
    public String start(){
        return "start";
    }

    @RequestMapping(value = "/practice",produces = "text/html;charset=UTF-8")
    public String practice(){
        return "practice";
    }

    @RequestMapping(value = "/master",produces = "text/html;charset=UTF-8")
    public String master(){
        return "master";
    }

    @RequestMapping(value = "/contest",produces = "text/html;charset=UTF-8")
    public String contest(){
        return "contest";
    }

    @RequestMapping(value = "/rank",produces = "text/html;charset=UTF-8")
    public String rank(){
        return "rank";
    }


}
