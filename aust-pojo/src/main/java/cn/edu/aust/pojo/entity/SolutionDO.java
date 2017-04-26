package cn.edu.aust.pojo.entity;

import java.util.Date;
import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "solution")
@Data
@NoArgsConstructor
@ToString
public class SolutionDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "problem_id")
    private Long problemId;

    @Column(name = "problem_title")
    private String problemTitle;

    private Long userId;

    private Integer memory;

    private Integer time;

    @Column(name = "code_length")
    private Double codeLength;

    private String language;

    @Column(name = "contest_id")
    private Integer contestId;

    private Integer verdict;

    private Integer testcase;

    private Date createdate;

    private Date modifydate;

    private String extend;
}