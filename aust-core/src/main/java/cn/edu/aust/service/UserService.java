package cn.edu.aust.service;

import java.util.List;

import cn.edu.aust.entity.User;

/**
 * @author Niu Li
 * @date 2016/9/2
 */
public interface UserService {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    /**
     * 查询要展示到首页的用户
     */
    List<User> selecttoShow();

    List<User> selectAll();

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUsername(String  username);

    User selectByEmail(String email);

    /**
     * 判断用户名是否被禁用
     */
    boolean usernameIsDisabled(String username);
}
