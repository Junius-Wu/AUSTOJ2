package cn.edu.aust.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import cn.edu.aust.common.constant.PosCode;
import cn.edu.aust.common.entity.ResultPackag;
import cn.edu.aust.dto.UserDTO;
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
    // TODO: 2017/1/29 缓存策略,最后统一使用redis
    @GetMapping(value = "/index/show/users",produces = "application/json; charset=UTF-8")
    public ResultPackag<?> indexToShow(){
        List<UserDTO> userDTOS = userService.queryToIndexShow();
        return new ResultPackag<List>(PosCode.OK,userDTOS);
    }
}
