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

@Table(name = "problem")
@Data
@NoArgsConstructor
@ToString
public class ProblemDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 所属目录
     */
    private Integer catelog;

    /**
     * 属于的阶段
     */
    private Integer stage;

    /**
     * 时间限制
     */
    @Column(name = "time_limit")
    private Integer timeLimit;

    /**
     * 内存限制
     */
    @Column(name = "memory_limit")
    private Integer memoryLimit;

    /**
     * 难度等级
     */
    private Integer difficulty;

    /**
     * 解决次数
     */
    private Integer solved;

    /**
     * 提交次数
     */
    private Integer submit;

    /**
     * 作者id
     */
    @Column(name = "author_id")
    private Integer authorId;
    /**
     * 作者名称
     */
    @Column(name = "author_name")
    private String authorName;

    /**
     * 题目类型
     */
    private Integer type;

    /**
     * 创建日期
     */
    private Date createdate;

    /**
     * 修改日期
     */
    private Date modifydate;

    private String description;

    private String input;
    private String output;
    @Column(name = "sample_input")
    private String sampleInput;
    @Column(name = "sample_output")
    private String sampleOutput;
    private String hit;
}