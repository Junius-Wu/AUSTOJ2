package cn.edu.aust.pojo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "solution")
public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "problem_id")
    private Integer problemId;

    private String username;

    private Integer memory;

    private Integer time;

    @Column(name = "code_length")
    private Integer codeLength;

    private Byte language;

    @Column(name = "contest_id")
    private Integer contestId;

    private Integer testcase;

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
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return memory
     */
    public Integer getMemory() {
        return memory;
    }

    /**
     * @param memory
     */
    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    /**
     * @return time
     */
    public Integer getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     * @return code_length
     */
    public Integer getCodeLength() {
        return codeLength;
    }

    /**
     * @param codeLength
     */
    public void setCodeLength(Integer codeLength) {
        this.codeLength = codeLength;
    }

    /**
     * @return language
     */
    public Byte getLanguage() {
        return language;
    }

    /**
     * @param language
     */
    public void setLanguage(Byte language) {
        this.language = language;
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
     * @return testcase
     */
    public Integer getTestcase() {
        return testcase;
    }

    /**
     * @param testcase
     */
    public void setTestcase(Integer testcase) {
        this.testcase = testcase;
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