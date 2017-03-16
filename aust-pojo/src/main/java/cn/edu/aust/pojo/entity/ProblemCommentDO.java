package cn.edu.aust.pojo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "problem_comment")
@Data
@NoArgsConstructor
@ToString
public class ProblemCommentDO {
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

}