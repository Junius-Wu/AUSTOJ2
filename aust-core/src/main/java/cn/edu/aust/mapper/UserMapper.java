package cn.edu.aust.mapper;

import java.util.List;

import cn.edu.aust.entity.User;

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
}