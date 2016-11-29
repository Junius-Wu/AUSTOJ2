package cn.edu.aust.common.entity;

import java.util.Date;

public class VoteLog {

    /**
     * 题目评论点赞
     */
    public final static Byte PRO_COMMENT = 1;
    /**
     * 文章点赞
     */
    public final static Byte ARTICLE = 2;
    /**
     * 文章评论点赞
     */
    public final static Byte TYPE_ART_COMMENT = 3;

    private Integer id;

    private Integer userId;

    private Byte type;

    private Integer otherId;

    private Byte status;

    private Date createtime;

    public VoteLog(Integer id, Integer userId, Byte type, Integer otherId, Byte status, Date createtime) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.otherId = otherId;
        this.status = status;
        this.createtime = createtime;
    }

    public VoteLog() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getOtherId() {
        return otherId;
    }

    public void setOtherId(Integer otherId) {
        this.otherId = otherId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}