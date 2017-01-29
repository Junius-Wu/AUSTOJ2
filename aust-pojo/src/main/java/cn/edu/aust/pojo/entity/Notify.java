package cn.edu.aust.pojo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "notify")
public class Notify {
    /**
     * 网站通知主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 所关联文章
     */
    @Column(name = "article_id")
    private Integer articleId;

    /**
     * 过期时间
     */
    private Date expiredate;

    /**
     * 状态1正常,0待发布,2已过期
     */
    private Byte status;

    /**
     * 创建日期
     */
    private Date createdate;

    /**
     * 修改日期
     */
    private Date modifydate;

    /**
     * 获取网站通知主键
     *
     * @return id - 网站通知主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置网站通知主键
     *
     * @param id 网站通知主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取所关联文章
     *
     * @return article_id - 所关联文章
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * 设置所关联文章
     *
     * @param articleId 所关联文章
     */
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    /**
     * 获取过期时间
     *
     * @return expiredate - 过期时间
     */
    public Date getExpiredate() {
        return expiredate;
    }

    /**
     * 设置过期时间
     *
     * @param expiredate 过期时间
     */
    public void setExpiredate(Date expiredate) {
        this.expiredate = expiredate;
    }

    /**
     * 获取状态1正常,0待发布,2已过期
     *
     * @return status - 状态1正常,0待发布,2已过期
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态1正常,0待发布,2已过期
     *
     * @param status 状态1正常,0待发布,2已过期
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取创建日期
     *
     * @return createdate - 创建日期
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * 设置创建日期
     *
     * @param createdate 创建日期
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * 获取修改日期
     *
     * @return modifydate - 修改日期
     */
    public Date getModifydate() {
        return modifydate;
    }

    /**
     * 设置修改日期
     *
     * @param modifydate 修改日期
     */
    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }
}