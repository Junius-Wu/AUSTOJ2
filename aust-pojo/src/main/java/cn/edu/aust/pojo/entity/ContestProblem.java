package cn.edu.aust.pojo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "contest_problem")
public class ContestProblem {
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

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return problem_id
     */
    public Integer getProblemId() {
        return problemId;
    }

    /**
     * @param problemId
     */
    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    /**
     * @return contest_id
     */
    public Integer getContestId() {
        return contestId;
    }

    /**
     * @param contestId
     */
    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return num
     */
    public String getNum() {
        return num;
    }

    /**
     * @param num
     */
    public void setNum(String num) {
        this.num = num;
    }

    /**
     * @return point
     */
    public Integer getPoint() {
        return point;
    }

    /**
     * @param point
     */
    public void setPoint(Integer point) {
        this.point = point;
    }

    /**
     * @return accepted
     */
    public Integer getAccepted() {
        return accepted;
    }

    /**
     * @param accepted
     */
    public void setAccepted(Integer accepted) {
        this.accepted = accepted;
    }

    /**
     * @return solved
     */
    public Integer getSolved() {
        return solved;
    }

    /**
     * @param solved
     */
    public void setSolved(Integer solved) {
        this.solved = solved;
    }

    /**
     * @return submit
     */
    public Integer getSubmit() {
        return submit;
    }

    /**
     * @param submit
     */
    public void setSubmit(Integer submit) {
        this.submit = submit;
    }

    /**
     * @return submit_user
     */
    public Integer getSubmitUser() {
        return submitUser;
    }

    /**
     * @param submitUser
     */
    public void setSubmitUser(Integer submitUser) {
        this.submitUser = submitUser;
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
}