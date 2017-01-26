package cn.edu.aust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
    @GetMapping(value = "/index",produces = "text/html;charset=UTF-8")
    public String index(){
        return "index";
    }

    /**
     * 前往首页
     */
    @GetMapping(value = "/",produces = "text/html;charset=UTF-8")
    public String index2(){
        return "index";
    }

}
