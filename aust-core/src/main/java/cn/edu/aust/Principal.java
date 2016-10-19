package cn.edu.aust;


import java.io.Serializable;
import java.util.Date;

import cn.edu.aust.common.entity.User;

/**
 * 身份属性
 * @author Niu Li
 * @date 2016/9/17
 */
public class Principal implements Serializable {

    private static final long serialVersionUID = 5798882004228239559L;

    /** ID */
    private Integer id;

    /** 用户名 */
    private String username;

    private String nickname;

    private Date modifyDate;
    /**
     * 构造方法
     *
     * @param id
     *            ID
     * @param username
     *            用户名
     */
    public Principal(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public Principal(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.modifyDate = user.getModifydate();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * 获取用户名
     *
     * @return 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username
     *            用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 重写toString方法
     *
     * @return 字符串
     */
    @Override
    public String toString() {
        return username;
    }
}
