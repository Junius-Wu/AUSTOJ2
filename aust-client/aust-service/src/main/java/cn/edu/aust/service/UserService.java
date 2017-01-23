package cn.edu.aust.service;

import cn.edu.aust.pojo.entity.User;

/**
 * 用户service类
 * @author Niu Li
 * @date 2017/1/22
 */
public interface UserService {
    /**
     * 得到当前客户端登录用户
     * @return 该用户
     */
    User getCurrent();


}
