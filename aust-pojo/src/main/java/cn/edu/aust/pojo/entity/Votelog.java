package cn.edu.aust.pojo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "votelog")
public class Votelog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 类型,1:题目留言点赞,2文章点赞,3文章留言点赞
     */
    private Byte type;

    /**
     * 点赞对象的id
     */
    @Column(name = "other_id")
    private Integer otherId;

    /**
     * 0取消,1有效
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取类型,1:题目留言点赞,2文章点赞,3文章留言点赞
     *
     * @return type - 类型,1:题目留言点赞,2文章点赞,3文章留言点赞
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置类型,1:题目留言点赞,2文章点赞,3文章留言点赞
     *
     * @param type 类型,1:题目留言点赞,2文章点赞,3文章留言点赞
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 获取点赞对象的id
     *
     * @return other_id - 点赞对象的id
     */
    public Integer getOtherId() {
        return otherId;
    }

    /**
     * 设置点赞对象的id
     *
     * @param otherId 点赞对象的id
     */
    public void setOtherId(Integer otherId) {
        this.otherId = otherId;
    }

    /**
     * 获取0取消,1有效
     *
     * @return status - 0取消,1有效
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置0取消,1有效
     *
     * @param status 0取消,1有效
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return createtime - 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}