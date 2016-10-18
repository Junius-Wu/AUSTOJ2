package cn.edu.aust.common.mapper;


import java.util.List;

import cn.edu.aust.common.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selecttoShow();

    List<User> selectAll();

    User selectByUsername(String username);

    User selectByEmail(String email);
}