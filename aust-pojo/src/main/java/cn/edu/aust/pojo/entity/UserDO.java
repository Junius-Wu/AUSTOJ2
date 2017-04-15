package cn.edu.aust.pojo.entity;

import java.util.Date;
import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "user")
@Data
@NoArgsConstructor
@ToString
public class UserDO {
    /**
     * 主键,自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户头像链接
     */
    private String avatar;

    /**
     * 用户名,不可修改,弃用,邮箱+密码登录
     */
    private String username;

    /**
     * 密码,sha256存储
     */
    private String password;

    /**
     * 昵称,可修改
     */
    private String nickname;

    /**
     * 授权id
     */
    private String openid;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 学校
     */
    private String school;

    /**
     * 学号
     */
    @Column(name = "student_no")
    private String studentNo;

    /**
     * 座右铭
     */
    private String motto;

    /**
     * 所得荣誉
     */
    private String honor;

    /**
     * 积分
     */
    private Integer point;

    /**
     * 常用语言1 C 2 C++ 3 Java
     */
    private String language;

    /**
     * 提交次数
     */
    private Integer submit;

    /**
     * 解决题目数
     */
    private Integer solved;

    /**
     * 首页展示 1是 0否
     */
    @Column(name = "is_show")
    private Integer isShow;

    /**
     * 博客链接
     */
    private String blog;

    /**
     * 登录IP
     */
    private String ip;

    /**
     * 是否锁定 1是 0否
     */
    @Column(name = "is_lock")
    private Integer isLock;

    /**
     * 登录失败次数
     */
    private Integer loginfail;

    /**
     * 锁定日期
     */
    private Date lockdate;

    /**
     * 创建日期
     */
    private Date createdate;

    /**
     * 修改日期
     */
    private Date modifydate;

    /**
     * 是否开放代码,1是 0否
     */
    @Column(name = "is_opensource")
    private Integer isOpensource;

    /**
     * 是否接收邮件,1是 0否
     */
    @Column(name = "is_email")
    private Integer isEmail;

    private Integer status;

    private Integer type;

    private String extend;
}