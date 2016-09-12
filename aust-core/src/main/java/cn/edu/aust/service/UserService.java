package cn.edu.aust.service;

import java.util.List;

import cn.edu.aust.entity.User;

/**
 * @author Niu Li
 * @date 2016/9/2
 */
public interface UserService {

    public int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selecttoShow();

    List<User> selectAll();

    User selectByUsername(String  username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
