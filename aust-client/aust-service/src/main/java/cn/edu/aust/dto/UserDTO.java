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
    private String avatar;
    private String nickname;
    private String honor;
    private String motto;


    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.avatar = user.getAvatar();
        this.nickname = user.getNickname();
        this.honor = user.getHonor();
        this.motto = user.getMotto();
    }
    public UserDTO() {
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
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

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", nickname='" + nickname + '\'' +
                ", honor='" + honor + '\'' +
                '}';
    }
}
