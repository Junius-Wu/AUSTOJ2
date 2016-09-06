package cn.edu.aust.entity;

import java.util.Date;

public class Solution {
    private Integer id;

    private Integer problemId;

    private String username;

    private Integer memory;

    private Integer time;

    private Integer codeLength;

    private Byte language;

    private Integer contestId;

    private Integer testcase;

    private Date createdate;

    private Date modifydate;

    public Solution(Integer id, Integer problemId, String username, Integer memory, Integer time, Integer codeLength, Byte language, Integer contestId, Integer testcase, Date createdate, Date modifydate) {
        this.id = id;
        this.problemId = problemId;
        this.username = username;
        this.memory = memory;
        this.time = time;
        this.codeLength = codeLength;
        this.language = language;
        this.contestId = contestId;
        this.testcase = testcase;
        this.createdate = createdate;
        this.modifydate = modifydate;
    }

    public Solution() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(Integer codeLength) {
        this.codeLength = codeLength;
    }

    public Byte getLanguage() {
        return language;
    }

    public void setLanguage(Byte language) {
        this.language = language;
    }

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }

    public Integer getTestcase() {
        return testcase;
    }

    public void setTestcase(Integer testcase) {
        this.testcase = testcase;
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