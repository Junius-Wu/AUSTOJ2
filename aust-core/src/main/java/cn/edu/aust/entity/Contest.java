package cn.edu.aust.entity;

import java.util.Date;

public class Contest {
    private Integer id;

    private String title;

    private Date startTime;

    private Date endTime;

    private Byte type;

    private String password;

    private String createUser;

    private Byte defunct;

    private Date createdate;

    private Date modifydate;

    private String description;

    public Contest(Integer id, String title, Date startTime, Date endTime, Byte type, String password, String createUser, Byte defunct, Date createdate, Date modifydate, String description) {
        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.password = password;
        this.createUser = createUser;
        this.defunct = defunct;
        this.createdate = createdate;
        this.modifydate = modifydate;
        this.description = description;
    }

    public Contest() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Byte getDefunct() {
        return defunct;
    }

    public void setDefunct(Byte defunct) {
        this.defunct = defunct;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}