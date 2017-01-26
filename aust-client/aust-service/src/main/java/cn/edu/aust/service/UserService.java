package cn.edu.aust.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.edu.aust.common.entity.Setting;
import cn.edu.aust.common.service.JedisClient;
import cn.edu.aust.common.util.SystemUtil;
import cn.edu.aust.dto.UserDTO;
import cn.edu.aust.pojo.entity.User;

/**
 * 用户service类
 * @author Niu Li
 * @date 2017/1/22
 */
@Service
public class UserService extends BaseService<User>{

    @Autowired
    private JedisClient jedisClient;

    /**
     * 得到当前客户端登录用户
     * @return 该用户
     */
    public User getCurrent(){
        ServletRequestAttributes context = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        UserDTO userDTO = (UserDTO) context.getAttribute(UserDTO.PRINCIPAL_ATTRIBUTE_NAME, RequestAttributes.SCOPE_SESSION);
        Long id = userDTO != null ? userDTO.getId() : null;
        return id == null?null:queryById(id);
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
            user = queryOne(user);
            if (user.getId() != null){
                return true;
            }
        }
        if (StringUtils.isNoneEmpty(email)){
            user.setEmail(email);
            user = queryOne(user);
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
