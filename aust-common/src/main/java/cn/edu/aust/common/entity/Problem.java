package cn.edu.aust.common.entity;

import java.util.Date;

public class Problem {
    private Integer id;

    private String title;

    private String keyword;

    private Byte catelog;

    private Byte stage;

    private Byte spj;

    private Integer timeLimit;

    private Integer memoryLimit;

    private Byte difficulty;

    private Integer accepted;

    private Integer solved;

    private Integer submit;

    private Integer submitUser;

    private Integer authorId;

    private Integer contestId;

    private Date createdate;

    private Date modifydate;

    public Problem(Integer id, String title, String keyword, Byte catelog, Byte stage, Byte spj, Integer timeLimit, Integer memoryLimit, Byte difficulty, Integer accepted, Integer solved, Integer submit, Integer submitUser, Integer authorId, Integer contestId, Date createdate, Date modifydate) {
        this.id = id;
        this.title = title;
        this.keyword = keyword;
        this.catelog = catelog;
        this.stage = stage;
        this.spj = spj;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
        this.difficulty = difficulty;
        this.accepted = accepted;
        this.solved = solved;
        this.submit = submit;
        this.submitUser = submitUser;
        this.authorId = authorId;
        this.contestId = contestId;
        this.createdate = createdate;
        this.modifydate = modifydate;
    }

    public Problem() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Byte getCatelog() {
        return catelog;
    }

    public void setCatelog(Byte catelog) {
        this.catelog = catelog;
    }

    public Byte getStage() {
        return stage;
    }

    public void setStage(Byte stage) {
        this.stage = stage;
    }

    public Byte getSpj() {
        return spj;
    }

    public void setSpj(Byte spj) {
        this.spj = spj;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(Integer memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public Byte getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Byte difficulty) {
        this.difficulty = difficulty;
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

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
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