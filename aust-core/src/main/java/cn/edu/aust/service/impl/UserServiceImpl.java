package cn.edu.aust.service.impl;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import javax.annotation.Resource;

import cn.edu.aust.Setting;
import cn.edu.aust.common.entity.User;
import cn.edu.aust.common.mapper.UserMapper;
import cn.edu.aust.service.UserService;
import cn.edu.aust.util.SystemUtil;

/**
 * 用户的服务层
 * @author Niu Li
 * @date 2016/9/11
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectToShow() {
        return userMapper.selecttoShow();
    }


    @Override
    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public User selectByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public boolean usernameIsDisabled(String username) {
        Assert.notNull(username);
        Setting setting = SystemUtil.getSetting();
        String[] disabledName = setting.getDisabledUsernames().split(",");
        for (String s : disabledName) {
            if (StringUtils.equalsIgnoreCase(s,username)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<User> selectRanks() {
        return userMapper.selectRanks();
    }
}
