package cn.edu.aust.pojo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "mail")
public class Mail {
    /**
     * 站内信主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer user;

    private Integer friend;

    private Integer sender;

    private Integer receiver;

    private Byte type;

    @Column(name = "send_time")
    private Date sendTime;

    @Column(name = "read_time")
    private Date readTime;

    private Byte status;

    private String content;

    /**
     * 获取站内信主键
     *
     * @return id - 站内信主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置站内信主键
     *
     * @param id 站内信主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return user
     */
    public Integer getUser() {
        return user;
    }

    /**
     * @param user
     */
    public void setUser(Integer user) {
        this.user = user;
    }

    /**
     * @return friend
     */
    public Integer getFriend() {
        return friend;
    }

    /**
     * @param friend
     */
    public void setFriend(Integer friend) {
        this.friend = friend;
    }

    /**
     * @return sender
     */
    public Integer getSender() {
        return sender;
    }

    /**
     * @param sender
     */
    public void setSender(Integer sender) {
        this.sender = sender;
    }

    /**
     * @return receiver
     */
    public Integer getReceiver() {
        return receiver;
    }

    /**
     * @param receiver
     */
    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    /**
     * @return type
     */
    public Byte getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * @return send_time
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * @param sendTime
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * @return read_time
     */
    public Date getReadTime() {
        return readTime;
    }

    /**
     * @param readTime
     */
    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    /**
     * @return status
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }
}