package cn.edu.aust.service.impl;

import org.springframework.stereotype.Service;

import cn.edu.aust.pojo.entity.User;
import cn.edu.aust.service.UserService;

/**
 * @author Niu Li
 * @date 2017/1/23
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Override
    public User getCurrent() {
        return null;
    }
}
