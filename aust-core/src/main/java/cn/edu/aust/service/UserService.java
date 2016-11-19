package cn.edu.aust.service;


import java.util.List;

import cn.edu.aust.common.entity.User;

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

    /**
     * 查找出展示在首页的用户
     * @return 用户合集,默认6个用户展示
     */
    List<User> selectToShow();

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 该用户,不存在则为null
     */
    User selectByUsername(String  username);

    /**
     * 根据邮箱号查找用户
     * @param email 邮箱号
     * @return 该用户,不存在则为null
     */
    User selectByEmail(String email);

    /**
     * 判断用户名是否被禁用
     * @param username 要判断的用户名
     * @return true禁用,false可用
     */
    boolean usernameIsDisabled(String username);

    /**
     * 查找出用户排名(非冻结用户,即defunct=0)
     * @return 用户合集(最大查找10000个用户)
     */
    List<User> selectRanks();

    /**
     * 得到当前登录用户
     * @return 该用户
     */
    User getCurrent();

}
