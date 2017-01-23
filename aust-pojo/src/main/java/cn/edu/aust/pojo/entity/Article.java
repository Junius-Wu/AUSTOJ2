package cn.edu.aust.pojo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 阅读数
     */
    private Integer viewcount;

    /**
     * 点赞数
     */
    private Integer likecount;

    /**
     * 暂且废除字段
     */
    @Column(name = "catelog_id")
    private Integer catelogId;

    /**
     * 0不置顶,1置顶
     */
    private Byte totop;

    /**
     * 0不展示,1展示
     */
    private Byte isshow;

    private Date createdate;

    private Date modifydate;

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
     * 获取关键词
     *
     * @return keyword - 关键词
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * 设置关键词
     *
     * @param keyword 关键词
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * 获取阅读数
     *
     * @return viewcount - 阅读数
     */
    public Integer getViewcount() {
        return viewcount;
    }

    /**
     * 设置阅读数
     *
     * @param viewcount 阅读数
     */
    public void setViewcount(Integer viewcount) {
        this.viewcount = viewcount;
    }

    /**
     * 获取点赞数
     *
     * @return likecount - 点赞数
     */
    public Integer getLikecount() {
        return likecount;
    }

    /**
     * 设置点赞数
     *
     * @param likecount 点赞数
     */
    public void setLikecount(Integer likecount) {
        this.likecount = likecount;
    }

    /**
     * 获取暂且废除字段
     *
     * @return catelog_id - 暂且废除字段
     */
    public Integer getCatelogId() {
        return catelogId;
    }

    /**
     * 设置暂且废除字段
     *
     * @param catelogId 暂且废除字段
     */
    public void setCatelogId(Integer catelogId) {
        this.catelogId = catelogId;
    }

    /**
     * 获取0不置顶,1置顶
     *
     * @return totop - 0不置顶,1置顶
     */
    public Byte getTotop() {
        return totop;
    }

    /**
     * 设置0不置顶,1置顶
     *
     * @param totop 0不置顶,1置顶
     */
    public void setTotop(Byte totop) {
        this.totop = totop;
    }

    /**
     * 获取0不展示,1展示
     *
     * @return isshow - 0不展示,1展示
     */
    public Byte getIsshow() {
        return isshow;
    }

    /**
     * 设置0不展示,1展示
     *
     * @param isshow 0不展示,1展示
     */
    public void setIsshow(Byte isshow) {
        this.isshow = isshow;
    }

    /**
     * @return createdate
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * @param createdate
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * @return modifydate
     */
    public Date getModifydate() {
        return modifydate;
    }

    /**
     * @param modifydate
     */
    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }
}