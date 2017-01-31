package cn.edu.aust.pojo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "problem")
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
    private Byte catelog;

    /**
     * 属于的阶段
     */
    private Byte stage;

    /**
     * 是否特殊判题
     */
    private Byte spj;

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
    private Byte difficulty;

    /**
     * 接收次数
     */
    private Integer accepted;

    /**
     * 解决次数
     */
    private Integer solved;

    /**
     * 提交次数
     */
    private Integer submit;

    /**
     * 提交人数
     */
    @Column(name = "submit_user")
    private Integer submitUser;

    /**
     * 作者id
     */
    @Column(name = "author_id")
    private Integer authorId;

    /**
     * 所属竞赛id
     */
    @Column(name = "contest_id")
    private Integer contestId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getSampleInput() {
        return sampleInput;
    }

    public void setSampleInput(String sampleInput) {
        this.sampleInput = sampleInput;
    }

    public String getSampleOutput() {
        return sampleOutput;
    }

    public void setSampleOutput(String sampleOutput) {
        this.sampleOutput = sampleOutput;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

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
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取关键词
     *
     * @return keyword - 关键词
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * 设置关键词
     *
     * @param keyword 关键词
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * 获取所属目录
     *
     * @return catelog - 所属目录
     */
    public Byte getCatelog() {
        return catelog;
    }

    /**
     * 设置所属目录
     *
     * @param catelog 所属目录
     */
    public void setCatelog(Byte catelog) {
        this.catelog = catelog;
    }

    /**
     * 获取属于的阶段
     *
     * @return stage - 属于的阶段
     */
    public Byte getStage() {
        return stage;
    }

    /**
     * 设置属于的阶段
     *
     * @param stage 属于的阶段
     */
    public void setStage(Byte stage) {
        this.stage = stage;
    }

    /**
     * 获取是否特殊判题
     *
     * @return spj - 是否特殊判题
     */
    public Byte getSpj() {
        return spj;
    }

    /**
     * 设置是否特殊判题
     *
     * @param spj 是否特殊判题
     */
    public void setSpj(Byte spj) {
        this.spj = spj;
    }

    /**
     * 获取时间限制
     *
     * @return time_limit - 时间限制
     */
    public Integer getTimeLimit() {
        return timeLimit;
    }

    /**
     * 设置时间限制
     *
     * @param timeLimit 时间限制
     */
    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    /**
     * 获取内存限制
     *
     * @return memory_limit - 内存限制
     */
    public Integer getMemoryLimit() {
        return memoryLimit;
    }

    /**
     * 设置内存限制
     *
     * @param memoryLimit 内存限制
     */
    public void setMemoryLimit(Integer memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    /**
     * 获取难度等级
     *
     * @return difficulty - 难度等级
     */
    public Byte getDifficulty() {
        return difficulty;
    }

    /**
     * 设置难度等级
     *
     * @param difficulty 难度等级
     */
    public void setDifficulty(Byte difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * 获取接收次数
     *
     * @return accepted - 接收次数
     */
    public Integer getAccepted() {
        return accepted;
    }

    /**
     * 设置接收次数
     *
     * @param accepted 接收次数
     */
    public void setAccepted(Integer accepted) {
        this.accepted = accepted;
    }

    /**
     * 获取解决次数
     *
     * @return solved - 解决次数
     */
    public Integer getSolved() {
        return solved;
    }

    /**
     * 设置解决次数
     *
     * @param solved 解决次数
     */
    public void setSolved(Integer solved) {
        this.solved = solved;
    }

    /**
     * 获取提交次数
     *
     * @return submit - 提交次数
     */
    public Integer getSubmit() {
        return submit;
    }

    /**
     * 设置提交次数
     *
     * @param submit 提交次数
     */
    public void setSubmit(Integer submit) {
        this.submit = submit;
    }

    /**
     * 获取提交人数
     *
     * @return submit_user - 提交人数
     */
    public Integer getSubmitUser() {
        return submitUser;
    }

    /**
     * 设置提交人数
     *
     * @param submitUser 提交人数
     */
    public void setSubmitUser(Integer submitUser) {
        this.submitUser = submitUser;
    }

    /**
     * 获取作者id
     *
     * @return author_id - 作者id
     */
    public Integer getAuthorId() {
        return authorId;
    }

    /**
     * 设置作者id
     *
     * @param authorId 作者id
     */
    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    /**
     * 获取所属竞赛id
     *
     * @return contest_id - 所属竞赛id
     */
    public Integer getContestId() {
        return contestId;
    }

    /**
     * 设置所属竞赛id
     *
     * @param contestId 所属竞赛id
     */
    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }

    /**
     * 获取创建日期
     *
     * @return createdate - 创建日期
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * 设置创建日期
     *
     * @param createdate 创建日期
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * 获取修改日期
     *
     * @return modifydate - 修改日期
     */
    public Date getModifydate() {
        return modifydate;
    }

    /**
     * 设置修改日期
     *
     * @param modifydate 修改日期
     */
    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }
}