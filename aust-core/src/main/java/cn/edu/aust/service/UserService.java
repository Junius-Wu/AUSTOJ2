package cn.edu.aust.service;

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

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
