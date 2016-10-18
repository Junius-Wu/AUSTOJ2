package cn.edu.aust.common.entity;

import java.util.Date;

public class Comment {
    private Integer id;

    private Integer user;

    private Integer firend;

    private Integer likecount;

    private Integer parentId;

    private Byte parentType;

    private Date createdate;

    private Date modifydate;

    private String content;

    public Comment(Integer id, Integer user, Integer firend, Integer likecount, Integer parentId, Byte parentType, Date createdate, Date modifydate, String content) {
        this.id = id;
        this.user = user;
        this.firend = firend;
        this.likecount = likecount;
        this.parentId = parentId;
        this.parentType = parentType;
        this.createdate = createdate;
        this.modifydate = modifydate;
        this.content = content;
    }

    public Comment() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getFirend() {
        return firend;
    }

    public void setFirend(Integer firend) {
        this.firend = firend;
    }

    public Integer getLikecount() {
        return likecount;
    }

    public void setLikecount(Integer likecount) {
        this.likecount = likecount;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Byte getParentType() {
        return parentType;
    }

    public void setParentType(Byte parentType) {
        this.parentType = parentType;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}