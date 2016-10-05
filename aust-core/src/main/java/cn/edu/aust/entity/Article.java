package cn.edu.aust.entity;

import java.util.Date;

public class Article {
    private Integer id;

    private String title;

    private Integer userId;

    private String nickname;

    private String keyword;

    private Integer catelogId;

    private Integer totop;

    private Integer isshow;

    private Date createdate;

    private Date modifydate;

    public Article(Integer id, String title, Integer userId, String nickname, String keyword, Integer catelogId, Integer totop, Integer isshow, Date createdate, Date modifydate) {
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.nickname = nickname;
        this.keyword = keyword;
        this.catelogId = catelogId;
        this.totop = totop;
        this.isshow = isshow;
        this.createdate = createdate;
        this.modifydate = modifydate;
    }

    public Article() {
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getCatelogId() {
        return catelogId;
    }

    public void setCatelogId(Integer catelogId) {
        this.catelogId = catelogId;
    }

    public Integer getTotop() {
        return totop;
    }

    public void setTotop(Integer totop) {
        this.totop = totop;
    }

    public Integer getisshow() {
        return isshow;
    }

    public void setisshow(Integer isshow) {
        this.isshow = isshow;
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