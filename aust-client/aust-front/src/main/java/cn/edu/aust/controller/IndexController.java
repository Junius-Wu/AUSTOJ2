package cn.edu.aust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 首页控制器
 * @author Niu Li
 * @date 2017/1/26
 */
@Controller
public class IndexController {
    /**
     * 前往首页
     */
    @GetMapping(value = {"/index","/"},produces = "text/html;charset=UTF-8")
    public String index(){
        return "index";
    }
    /**
     * 前往start页面
     */
    @GetMapping(value = "/start",produces = "text/html;charset=UTF-8")
    public String start(){
        return "start";
    }
    /**
     * 前往practice页面
     */
    @GetMapping(value = "/practice",produces = "text/html;charset=UTF-8")
    public String practice(){
        return "practice";
    }
    /**
     * 前往master页面
     */
    @GetMapping(value = "/master",produces = "text/html;charset=UTF-8")
    public String master(){
        return "master";
    }
    /**
     * 前往rank页面
     */
    @GetMapping(value = "/rank",produces = "text/html;charset=UTF-8")
    public String rank(){
        return "rank";
    }
    /**
     * 前往error页面
     */
    @GetMapping(value = "/error",produces = "text/html;charset=UTF-8")
    public String error(HttpServletRequest request){
        return "error";
    }

}
