package cn.edu.aust.service;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import javax.annotation.Resource;

import cn.edu.aust.common.entity.Setting;
import cn.edu.aust.common.service.JedisClient;
import cn.edu.aust.common.util.SystemUtil;
import cn.edu.aust.dto.UserDTO;
import cn.edu.aust.dto.UserRankDTO;
import cn.edu.aust.mapper.UserMapper;
import cn.edu.aust.pojo.entity.User;

/**
 * 用户service类
 * @author Niu Li
 * @date 2017/1/22
 */
@Service
public class UserService extends BaseService<User>{

    @Resource
    private JedisClient jedisClient;
    @Resource
    private UserMapper userMapper;

    /**
     * 得到当前客户端登录用户
     * @return 该用户
     */
    public User getCurrent(){
        ServletRequestAttributes context = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        UserDTO userDTO = (UserDTO) context.getAttribute(UserDTO.PRINCIPAL_ATTRIBUTE_NAME, RequestAttributes.SCOPE_SESSION);
        Long id = userDTO != null ? userDTO.getId() : null;
        return id == null?null:userMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询用户排名
     * @return 排名后的用户
     */
    public List<UserRankDTO> queryForRank(){
        List<User> users = userMapper.queryForRank();
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(users,new TypeToken<List<UserRankDTO>>(){}.getType());
    }

    /**
     * 查询展示到首页的用户
     * @return 展示到首页用户
     */
    public List<UserDTO> queryToIndexShow(){
        List<User> users = userMapper.queryToIndexShow();
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(users,new TypeToken<List<UserDTO>>(){}.getType());
    }

    /**
     * 判断用户名或者邮箱是否存在
     * @param username 用户名
     * @param email 邮箱
     * @return true存在
     */
    public boolean judgeUsernameOrEmail(String username,String email){
        User user = new User();
        if (StringUtils.isNoneEmpty(username)){
            user.setUsername(username);
            user = userMapper.selectOne(user);
            if (user.getId() != null){
                return true;
            }
        }
        if (StringUtils.isNoneEmpty(email)){
            user.setEmail(email);
            user = userMapper.selectOne(user);
        }
        return user != null;
    }

    /**
     * 判断用户名是否被禁用
     * @param username 用户名
     * @return true被禁用
     */
    public boolean usernameIsDisabled(String username) {
        if (StringUtils.isEmpty(username)) return false;

        Setting setting = SystemUtil.getSetting(jedisClient);
        String[] disabledName = setting.getDisabledUsernames().split(",");
        for (String s : disabledName) {
            if (StringUtils.equalsIgnoreCase(s,username)){
                return true;
            }
        }
        return false;
    }

}
