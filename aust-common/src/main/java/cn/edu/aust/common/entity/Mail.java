package cn.edu.aust.common.entity;

import java.util.Date;

public class Mail {
    private Integer id;

    private Integer user;

    private Integer friend;

    private Integer sender;

    private Integer receiver;

    private Byte type;

    private Date sendTime;

    private Date readTime;

    private Byte status;

    private String content;

    public Mail(Integer id, Integer user, Integer friend, Integer sender, Integer receiver, Byte type, Date sendTime, Date readTime, Byte status, String content) {
        this.id = id;
        this.user = user;
        this.friend = friend;
        this.sender = sender;
        this.receiver = receiver;
        this.type = type;
        this.sendTime = sendTime;
        this.readTime = readTime;
        this.status = status;
        this.content = content;
    }

    public Mail() {
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

    public Integer getFriend() {
        return friend;
    }

    public void setFriend(Integer friend) {
        this.friend = friend;
    }

    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}