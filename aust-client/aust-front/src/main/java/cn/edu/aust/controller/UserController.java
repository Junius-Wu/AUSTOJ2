package cn.edu.aust.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.aust.common.entity.Result;
import cn.edu.aust.service.UserService;

/**
 * @author Niu Li
 * @date 2017/1/26
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 获取首页展示用户
     */
    @GetMapping(value = "/index/show/users",produces = "application/json; charset=UTF-8")
    public Result<?> indexToShow(){

        return null;
    }
}
