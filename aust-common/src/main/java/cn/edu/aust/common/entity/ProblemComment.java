package cn.edu.aust.common.entity;

import java.util.Date;

public class ProblemComment {
    private Integer id;

    private Integer problemId;

    private Integer userId;

    private Integer firendId;

    private Integer likecount;

    private Integer rootId;

    private Byte status;

    private Date createdate;

    private Date modifydate;

    private String content;

    public ProblemComment(Integer id, Integer problemId, Integer userId, Integer firendId, Integer likecount, Integer rootId, Byte status, Date createdate, Date modifydate, String content) {
        this.id = id;
        this.problemId = problemId;
        this.userId = userId;
        this.firendId = firendId;
        this.likecount = likecount;
        this.rootId = rootId;
        this.status = status;
        this.createdate = createdate;
        this.modifydate = modifydate;
        this.content = content;
    }

    public ProblemComment() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFirendId() {
        return firendId;
    }

    public void setFirendId(Integer firendId) {
        this.firendId = firendId;
    }

    public Integer getLikecount() {
        return likecount;
    }

    public void setLikecount(Integer likecount) {
        this.likecount = likecount;
    }

    public Integer getRootId() {
        return rootId;
    }

    public void setRootId(Integer rootId) {
        this.rootId = rootId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}