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

@Table(name = "contest_problem")
@Data
@NoArgsConstructor
@ToString
public class ContestProblemDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "problem_id")
    private Long problemId;

    @Column(name = "problem_title")
    private String problemTitle;

    @Column(name = "contest_id")
    private Integer contestId;

    private String num;

    private Integer point;

    private Integer submit;

    private Integer solved;

    private Integer status;

    private Date createdate;

    private Date modifydate;
}