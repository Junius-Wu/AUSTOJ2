package cn.edu.aust.pojo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user")
public class User {
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
     * QQ登录的授权id
     */
    @Column(name = "qq_openid")
    private String qqOpenid;

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
    private Byte language;

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
    private Byte isShow;

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
    private Byte isLock;

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
     * 是否冻结,1正常,0冻结,2待验证
     */
    @Column(name = "is_defunct")
    private Byte isDefunct;

    /**
     * 是否开放代码,1是 0否
     */
    @Column(name = "is_opensource")
    private Byte isOpensource;

    /**
     * 是否接收邮件,1是 0否
     */
    @Column(name = "is_email")
    private Byte isEmail;

    /**
     * 获取主键,自增
     *
     * @return id - 主键,自增
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键,自增
     *
     * @param id 主键,自增
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户头像链接
     *
     * @return avatar - 用户头像链接
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置用户头像链接
     *
     * @param avatar 用户头像链接
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取用户名,不可修改,弃用,邮箱+密码登录
     *
     * @return username - 用户名,不可修改,弃用,邮箱+密码登录
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名,不可修改,弃用,邮箱+密码登录
     *
     * @param username 用户名,不可修改,弃用,邮箱+密码登录
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码,sha256存储
     *
     * @return password - 密码,sha256存储
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码,sha256存储
     *
     * @param password 密码,sha256存储
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取昵称,可修改
     *
     * @return nickname - 昵称,可修改
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称,可修改
     *
     * @param nickname 昵称,可修改
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取QQ登录的授权id
     *
     * @return qq_openid - QQ登录的授权id
     */
    public String getQqOpenid() {
        return qqOpenid;
    }

    /**
     * 设置QQ登录的授权id
     *
     * @param qqOpenid QQ登录的授权id
     */
    public void setQqOpenid(String qqOpenid) {
        this.qqOpenid = qqOpenid;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取学校
     *
     * @return school - 学校
     */
    public String getSchool() {
        return school;
    }

    /**
     * 设置学校
     *
     * @param school 学校
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * 获取学号
     *
     * @return student_no - 学号
     */
    public String getStudentNo() {
        return studentNo;
    }

    /**
     * 设置学号
     *
     * @param studentNo 学号
     */
    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    /**
     * 获取座右铭
     *
     * @return motto - 座右铭
     */
    public String getMotto() {
        return motto;
    }

    /**
     * 设置座右铭
     *
     * @param motto 座右铭
     */
    public void setMotto(String motto) {
        this.motto = motto;
    }

    /**
     * 获取所得荣誉
     *
     * @return honor - 所得荣誉
     */
    public String getHonor() {
        return honor;
    }

    /**
     * 设置所得荣誉
     *
     * @param honor 所得荣誉
     */
    public void setHonor(String honor) {
        this.honor = honor;
    }

    /**
     * 获取积分
     *
     * @return point - 积分
     */
    public Integer getPoint() {
        return point;
    }

    /**
     * 设置积分
     *
     * @param point 积分
     */
    public void setPoint(Integer point) {
        this.point = point;
    }

    /**
     * 获取常用语言1 C 2 C++ 3 Java
     *
     * @return language - 常用语言1 C 2 C++ 3 Java
     */
    public Byte getLanguage() {
        return language;
    }

    /**
     * 设置常用语言1 C 2 C++ 3 Java
     *
     * @param language 常用语言1 C 2 C++ 3 Java
     */
    public void setLanguage(Byte language) {
        this.language = language;
    }

    /**
     * 获取提交次数
     *
     * @return submit - 提交次数
     */
    public Integer getSubmit() {
        return submit;
    }

    /**
     * 设置提交次数
     *
     * @param submit 提交次数
     */
    public void setSubmit(Integer submit) {
        this.submit = submit;
    }

    /**
     * 获取解决题目数
     *
     * @return solved - 解决题目数
     */
    public Integer getSolved() {
        return solved;
    }

    /**
     * 设置解决题目数
     *
     * @param solved 解决题目数
     */
    public void setSolved(Integer solved) {
        this.solved = solved;
    }

    /**
     * 获取首页展示 1是 0否
     *
     * @return is_show - 首页展示 1是 0否
     */
    public Byte getIsShow() {
        return isShow;
    }

    /**
     * 设置首页展示 1是 0否
     *
     * @param isShow 首页展示 1是 0否
     */
    public void setIsShow(Byte isShow) {
        this.isShow = isShow;
    }

    /**
     * 获取博客链接
     *
     * @return blog - 博客链接
     */
    public String getBlog() {
        return blog;
    }

    /**
     * 设置博客链接
     *
     * @param blog 博客链接
     */
    public void setBlog(String blog) {
        this.blog = blog;
    }

    /**
     * 获取登录IP
     *
     * @return ip - 登录IP
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置登录IP
     *
     * @param ip 登录IP
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取是否锁定 1是 0否
     *
     * @return is_lock - 是否锁定 1是 0否
     */
    public Byte getIsLock() {
        return isLock;
    }

    /**
     * 设置是否锁定 1是 0否
     *
     * @param isLock 是否锁定 1是 0否
     */
    public void setIsLock(Byte isLock) {
        this.isLock = isLock;
    }

    /**
     * 获取登录失败次数
     *
     * @return loginfail - 登录失败次数
     */
    public Integer getLoginfail() {
        return loginfail;
    }

    /**
     * 设置登录失败次数
     *
     * @param loginfail 登录失败次数
     */
    public void setLoginfail(Integer loginfail) {
        this.loginfail = loginfail;
    }

    /**
     * 获取锁定日期
     *
     * @return lockdate - 锁定日期
     */
    public Date getLockdate() {
        return lockdate;
    }

    /**
     * 设置锁定日期
     *
     * @param lockdate 锁定日期
     */
    public void setLockdate(Date lockdate) {
        this.lockdate = lockdate;
    }

    /**
     * 获取创建日期
     *
     * @return createdate - 创建日期
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * 设置创建日期
     *
     * @param createdate 创建日期
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * 获取修改日期
     *
     * @return modifydate - 修改日期
     */
    public Date getModifydate() {
        return modifydate;
    }

    /**
     * 设置修改日期
     *
     * @param modifydate 修改日期
     */
    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    /**
     * 获取是否冻结,1正常,0冻结,2待验证
     *
     * @return is_defunct - 是否冻结,1正常,0冻结,2待验证
     */
    public Byte getIsDefunct() {
        return isDefunct;
    }

    /**
     * 设置是否冻结,1正常,0冻结,2待验证
     *
     * @param isDefunct 是否冻结,1正常,0冻结,2待验证
     */
    public void setIsDefunct(Byte isDefunct) {
        this.isDefunct = isDefunct;
    }

    /**
     * 获取是否开放代码,1是 0否
     *
     * @return is_opensource - 是否开放代码,1是 0否
     */
    public Byte getIsOpensource() {
        return isOpensource;
    }

    /**
     * 设置是否开放代码,1是 0否
     *
     * @param isOpensource 是否开放代码,1是 0否
     */
    public void setIsOpensource(Byte isOpensource) {
        this.isOpensource = isOpensource;
    }

    /**
     * 获取是否接收邮件,1是 0否
     *
     * @return is_email - 是否接收邮件,1是 0否
     */
    public Byte getIsEmail() {
        return isEmail;
    }

    /**
     * 设置是否接收邮件,1是 0否
     *
     * @param isEmail 是否接收邮件,1是 0否
     */
    public void setIsEmail(Byte isEmail) {
        this.isEmail = isEmail;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", qqOpenid='" + qqOpenid + '\'' +
                ", email='" + email + '\'' +
                ", school='" + school + '\'' +
                ", studentNo='" + studentNo + '\'' +
                ", motto='" + motto + '\'' +
                ", honor='" + honor + '\'' +
                ", point=" + point +
                ", language=" + language +
                ", submit=" + submit +
                ", solved=" + solved +
                ", isShow=" + isShow +
                ", blog='" + blog + '\'' +
                ", ip='" + ip + '\'' +
                ", isLock=" + isLock +
                ", loginfail=" + loginfail +
                ", lockdate=" + lockdate +
                ", createdate=" + createdate +
                ", modifydate=" + modifydate +
                ", isDefunct=" + isDefunct +
                ", isOpensource=" + isOpensource +
                ", isEmail=" + isEmail +
                '}';
    }
}