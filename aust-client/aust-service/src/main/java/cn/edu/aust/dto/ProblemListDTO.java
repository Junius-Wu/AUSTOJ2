package cn.edu.aust.dto;

/**
 * 用于列表显示
 * @author Niu Li
 * @date 2017/1/29
 */
public class ProblemListDTO {
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
    private String catelogName;
    /**
     * 属于的阶段
     */
    private Byte stage;
    /**
     * 难度等级
     */
    private Byte difficulty;
    /**
     * 解决次数
     */
    private Integer solved;

    /**
     * 提交次数
     */
    private Integer submit;
    /**
     * 题目作者
     */
    private String author;

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

    public String getCatelogName() {
        return catelogName;
    }

    public void setCatelogName(String catelogName) {
        this.catelogName = catelogName;
    }

    public Byte getStage() {
        return stage;
    }

    public void setStage(Byte stage) {
        this.stage = stage;
    }

    public Byte getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Byte difficulty) {
        this.difficulty = difficulty;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
