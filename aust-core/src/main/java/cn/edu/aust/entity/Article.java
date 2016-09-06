package cn.edu.aust.entity;

import java.util.Date;

public class Article {
    private Integer id;

    private String title;

    private Integer userId;

    private String nickname;

    private String keyword;

    private Integer catelogId;

    private Byte totop;

    private Byte show;

    private Date createdate;

    private Date modifydate;

    public Article(Integer id, String title, Integer userId, String nickname, String keyword, Integer catelogId, Byte totop, Byte show, Date createdate, Date modifydate) {
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.nickname = nickname;
        this.keyword = keyword;
        this.catelogId = catelogId;
        this.totop = totop;
        this.show = show;
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

    public Byte getTotop() {
        return totop;
    }

    public void setTotop(Byte totop) {
        this.totop = totop;
    }

    public Byte getShow() {
        return show;
    }

    public void setShow(Byte show) {
        this.show = show;
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