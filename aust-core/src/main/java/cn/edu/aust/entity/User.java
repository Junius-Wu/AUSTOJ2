package cn.edu.aust.entity;

import java.util.Date;

public class User {
    private Integer id;

    private String avatar;

    private String username;

    private String password;

    private String nickname;

    private String email;

    private String school;

    private String stuNo;

    private String motto;

    private String honor;

    private Integer point;

    private Byte language;

    private Integer submit;

    private Integer solved;

    private Byte show;

    private String blog;

    private String ip;

    private Date createdate;

    private Date modifydate;

    private Byte defunct;

    private Byte opensource;

    public User(Integer id, String avatar, String username, String password, String nickname, String email, String school, String stuNo, String motto, String honor, Integer point, Byte language, Integer submit, Integer solved, Byte show, String blog, String ip, Date createdate, Date modifydate, Byte defunct, Byte opensource) {
        this.id = id;
        this.avatar = avatar;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.school = school;
        this.stuNo = stuNo;
        this.motto = motto;
        this.honor = honor;
        this.point = point;
        this.language = language;
        this.submit = submit;
        this.solved = solved;
        this.show = show;
        this.blog = blog;
        this.ip = ip;
        this.createdate = createdate;
        this.modifydate = modifydate;
        this.defunct = defunct;
        this.opensource = opensource;
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

    public Byte getLanguage() {
        return language;
    }

    public void setLanguage(Byte language) {
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

    public Byte getShow() {
        return show;
    }

    public void setShow(Byte show) {
        this.show = show;
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

    public Byte getDefunct() {
        return defunct;
    }

    public void setDefunct(Byte defunct) {
        this.defunct = defunct;
    }

    public Byte getOpensource() {
        return opensource;
    }

    public void setOpensource(Byte opensource) {
        this.opensource = opensource;
    }
}