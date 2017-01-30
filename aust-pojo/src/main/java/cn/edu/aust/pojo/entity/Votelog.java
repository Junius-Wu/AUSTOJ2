package cn.edu.aust.pojo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "votelog")
public class Votelog {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 类型,1:题目留言点赞,2文章点赞,3文章留言点赞
     */
    private Byte type;

    /**
     * 点赞对象的id
     */
    @Column(name = "other_id")
    private Long otherId;

    /**
     * 0取消,1有效
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date modifytime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
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
    public Long getOtherId() {
        return otherId;
    }

    /**
     * 设置点赞对象的id
     *
     * @param otherId 点赞对象的id
     */
    public void setOtherId(Long otherId) {
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

    /**
     * 获取修改时间
     *
     * @return modifytime - 修改时间
     */
    public Date getModifytime() {
        return modifytime;
    }

    /**
     * 设置修改时间
     *
     * @param modifytime 修改时间
     */
    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }
}