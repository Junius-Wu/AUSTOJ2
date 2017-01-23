package cn.edu.aust.pojo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "problem_comment")
public class ProblemComment {
    /**
     * 题目评论主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 对应题目的id
     */
    @Column(name = "problem_id")
    private Integer problemId;

    /**
     * 留言用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 对方的id
     */
    @Column(name = "firend_id")
    private Integer firendId;

    /**
     * 点赞数
     */
    private Integer likecount;

    /**
     * 该留言root节点
     */
    @Column(name = "root_id")
    private Integer rootId;

    /**
     * 1正常,10审核
     */
    private Byte status;

    private Date createdate;

    private Date modifydate;

    /**
     * 留言内容
     */
    private String content;

    /**
     * 获取题目评论主键
     *
     * @return id - 题目评论主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置题目评论主键
     *
     * @param id 题目评论主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取对应题目的id
     *
     * @return problem_id - 对应题目的id
     */
    public Integer getProblemId() {
        return problemId;
    }

    /**
     * 设置对应题目的id
     *
     * @param problemId 对应题目的id
     */
    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    /**
     * 获取留言用户id
     *
     * @return user_id - 留言用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置留言用户id
     *
     * @param userId 留言用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取对方的id
     *
     * @return firend_id - 对方的id
     */
    public Integer getFirendId() {
        return firendId;
    }

    /**
     * 设置对方的id
     *
     * @param firendId 对方的id
     */
    public void setFirendId(Integer firendId) {
        this.firendId = firendId;
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
     * 获取该留言root节点
     *
     * @return root_id - 该留言root节点
     */
    public Integer getRootId() {
        return rootId;
    }

    /**
     * 设置该留言root节点
     *
     * @param rootId 该留言root节点
     */
    public void setRootId(Integer rootId) {
        this.rootId = rootId;
    }

    /**
     * 获取1正常,10审核
     *
     * @return status - 1正常,10审核
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置1正常,10审核
     *
     * @param status 1正常,10审核
     */
    public void setStatus(Byte status) {
        this.status = status;
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

    /**
     * 获取留言内容
     *
     * @return content - 留言内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置留言内容
     *
     * @param content 留言内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}