package cn.edu.aust.dto;

import cn.edu.aust.pojo.entity.User;

/**
 * @author Niu Li
 * @date 2017/1/25
 */
public class UserDTO {
    /**
     * 认证名称
     */
    public static final String PRINCIPAL_ATTRIBUTE_NAME = "userinfo";
    /**
     * 用户名cookies
     */
    public static final String USERNAME_COOKIE_NAME = "username";
    /**
     * 用户昵称cookies
     */
    public static final String NICKNAME_COOKIE_NAME = "nickname";


    private Long id;
    private String email;

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
