package cn.edu.aust.pojo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "reportlog")
public class Reportlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 举报用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 举报类型,1题目评论,2文章,3文章评论
     */
    private Byte type;

    /**
     * 举报内容的id
     */
    @Column(name = "other_id")
    private Integer otherId;

    /**
     * 1举报,0撤销
     */
    private Byte status;

    /**
     * 时间
     */
    private Date createdate;

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
     * 获取举报用户id
     *
     * @return user_id - 举报用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置举报用户id
     *
     * @param userId 举报用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取举报类型,1题目评论,2文章,3文章评论
     *
     * @return type - 举报类型,1题目评论,2文章,3文章评论
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置举报类型,1题目评论,2文章,3文章评论
     *
     * @param type 举报类型,1题目评论,2文章,3文章评论
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 获取举报内容的id
     *
     * @return other_id - 举报内容的id
     */
    public Integer getOtherId() {
        return otherId;
    }

    /**
     * 设置举报内容的id
     *
     * @param otherId 举报内容的id
     */
    public void setOtherId(Integer otherId) {
        this.otherId = otherId;
    }

    /**
     * 获取1举报,0撤销
     *
     * @return status - 1举报,0撤销
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置1举报,0撤销
     *
     * @param status 1举报,0撤销
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取时间
     *
     * @return createdate - 时间
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * 设置时间
     *
     * @param createdate 时间
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}