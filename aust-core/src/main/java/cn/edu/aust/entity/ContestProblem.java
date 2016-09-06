package cn.edu.aust.entity;

import java.util.Date;

public class ContestProblem {
    private Integer id;

    private Integer problemId;

    private Integer contestId;

    private String title;

    private String num;

    private Integer point;

    private Integer accepted;

    private Integer solved;

    private Integer submit;

    private Integer submitUser;

    private Date createdate;

    private Date modifydate;

    public ContestProblem(Integer id, Integer problemId, Integer contestId, String title, String num, Integer point, Integer accepted, Integer solved, Integer submit, Integer submitUser, Date createdate, Date modifydate) {
        this.id = id;
        this.problemId = problemId;
        this.contestId = contestId;
        this.title = title;
        this.num = num;
        this.point = point;
        this.accepted = accepted;
        this.solved = solved;
        this.submit = submit;
        this.submitUser = submitUser;
        this.createdate = createdate;
        this.modifydate = modifydate;
    }

    public ContestProblem() {
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

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getAccepted() {
        return accepted;
    }

    public void setAccepted(Integer accepted) {
        this.accepted = accepted;
    }

    public Integer getSolved() {
        return solved;
    }

    public void setSolved(Integer solved) {
        this.solved = solved;
    }

    public Integer getSubmit() {
        return submit;
    }

    public void setSubmit(Integer submit) {
        this.submit = submit;
    }

    public Integer getSubmitUser() {
        return submitUser;
    }

    public void setSubmitUser(Integer submitUser) {
        this.submitUser = submitUser;
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
}