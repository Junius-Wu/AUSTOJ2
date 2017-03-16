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
    private Integer problemId;

    @Column(name = "contest_id")
    private Integer contestId;

    private String title;

    private String num;

    private Integer point;

    private Integer accepted;

    private Integer solved;

    private Integer submit;

    @Column(name = "submit_user")
    private Integer submitUser;

    private Date createdate;

    private Date modifydate;

}