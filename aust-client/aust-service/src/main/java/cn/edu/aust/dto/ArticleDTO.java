package cn.edu.aust.dto;

import java.util.Date;

/**
 * 文章详细
 * @author Niu Li
 * @date 2017/1/30
 */
public class ArticleDTO {

    public static final String ARTICLEHIT_COOKIES = "view_articles";

    private Long id;

    private String title;

    private String keyword;

    private Integer viewcount;

    private Byte isTop;

    private Date createdate;

    private Integer likecount;

    private String nickname;

    private String htmlContent;

    public Integer getViewcount() {
        return viewcount;
    }

    public void setViewcount(Integer viewcount) {
        this.viewcount = viewcount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Byte getIsTop() {
        return isTop;
    }

    public void setIsTop(Byte isTop) {
        this.isTop = isTop;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getLikecount() {
        return likecount;
    }

    public void setLikecount(Integer likecount) {
        this.likecount = likecount;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }
}
