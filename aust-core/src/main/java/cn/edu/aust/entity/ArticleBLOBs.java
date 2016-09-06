package cn.edu.aust.entity;

import java.util.Date;

public class ArticleBLOBs extends Article {
    private String summary;

    private String content;

    public ArticleBLOBs(Integer id, String title, Integer userId, String nickname, String keyword, Integer catelogId, Byte totop, Byte show, Date createdate, Date modifydate, String summary, String content) {
        super(id, title, userId, nickname, keyword, catelogId, totop, show, createdate, modifydate);
        this.summary = summary;
        this.content = content;
    }

    public ArticleBLOBs() {
        super();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}