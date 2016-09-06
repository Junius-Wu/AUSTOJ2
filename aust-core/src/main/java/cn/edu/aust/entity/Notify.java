package cn.edu.aust.entity;

import java.util.Date;

public class Notify {
    private Integer id;

    private String title;

    private Integer articleId;

    private Date createdate;

    private Date modifydate;

    public Notify(Integer id, String title, Integer articleId, Date createdate, Date modifydate) {
        this.id = id;
        this.title = title;
        this.articleId = articleId;
        this.createdate = createdate;
        this.modifydate = modifydate;
    }

    public Notify() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
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
}