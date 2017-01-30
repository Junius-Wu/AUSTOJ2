package cn.edu.aust.pojo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "article")
public class Article {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 摘要
     */
    private String summary;
    /**
     * html内容
     */
    @Column(name = "html_content")
    private String htmlContent;
    /**
     * markdown内容
     */
    private String content;
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
     * 关键词,都好分隔
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
    @Column(name = "is_top")
    private Byte isTop;

    /**
     * 0不展示,1展示
     */
    @Column(name = "is_show")
    private Byte isShow;

    private Date createdate;

    private Date modifydate;


    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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
     * 获取关键词,都好分隔
     *
     * @return keyword - 关键词,都好分隔
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * 设置关键词,都好分隔
     *
     * @param keyword 关键词,都好分隔
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
     * @return is_top - 0不置顶,1置顶
     */
    public Byte getIsTop() {
        return isTop;
    }

    /**
     * 设置0不置顶,1置顶
     *
     * @param isTop 0不置顶,1置顶
     */
    public void setIsTop(Byte isTop) {
        this.isTop = isTop;
    }

    /**
     * 获取0不展示,1展示
     *
     * @return is_show - 0不展示,1展示
     */
    public Byte getIsShow() {
        return isShow;
    }

    /**
     * 设置0不展示,1展示
     *
     * @param isShow 0不展示,1展示
     */
    public void setIsShow(Byte isShow) {
        this.isShow = isShow;
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