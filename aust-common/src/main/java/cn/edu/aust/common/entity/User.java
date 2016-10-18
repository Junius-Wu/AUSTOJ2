package cn.edu.aust.common.entity;


import org.hibernate.validator.constraints.Email;

import java.util.Date;

public class User {

    /** "身份信息"属性名称 */
    public static final String PRINCIPAL_ATTRIBUTE_NAME = "userpri";

    /** "用户名"Cookie名称 */
    public static final String USERNAME_COOKIE_NAME = "username";

    /** "昵称"Cookie名称 */
    public static final String NICKNAME_COOKIE_NAME = "nickname";

    private Integer id;
    private String avatar;
//    @Pattern(regexp = "^[a-zA-Z0-9_]{3,16}$",message = "用户名不合法")
    private String username;
//    @Pattern(regexp = "^[@A-Za-z0-9!#$%^&*.~]{6,22}$",message = "密码格式不匹配")
    private String password;

    private String nickname;
    @Email(regexp = "^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$",message = "email格式不合法")
    private String email;

    private String school;

    private String stuNo;

    private String motto;

    private String honor;

    private Integer point;

    private Integer language;

    private Integer submit;

    private Integer solved;

    private Boolean isshow;

    private String blog;

    private String ip;

    private Boolean islock;

    private Integer loginfail;

    private Date lockdate;

    private Date createdate;

    private Date modifydate;

    private Boolean defunct;

    private Boolean opensource;

    public Integer getLoginfail() {
        return loginfail;
    }

    public void setLoginfail(Integer loginfail) {
        this.loginfail = loginfail;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public Integer getSubmit() {
        return submit;
    }

    public void setSubmit(Integer submit) {
        this.submit = submit;
    }

    public Integer getSolved() {
        return solved;
    }

    public void setSolved(Integer solved) {
        this.solved = solved;
    }

    public Boolean getIsshow() {
        return isshow;
    }

    public void setIsshow(Boolean isshow) {
        this.isshow = isshow;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Boolean getIslock() {
        return islock;
    }

    public void setIslock(Boolean islock) {
        this.islock = islock;
    }

    public Date getLockdate() {
        return lockdate;
    }

    public void setLockdate(Date lockdate) {
        this.lockdate = lockdate;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public Boolean getDefunct() {
        return defunct;
    }

    public void setDefunct(Boolean defunct) {
        this.defunct = defunct;
    }

    public Boolean getOpensource() {
        return opensource;
    }

    public void setOpensource(Boolean opensource) {
        this.opensource = opensource;
    }
}