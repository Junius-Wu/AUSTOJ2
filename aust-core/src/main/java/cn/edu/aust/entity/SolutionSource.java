package cn.edu.aust.entity;

public class SolutionSource {
    private Integer id;

    private Integer solutionId;

    private String source;

    public SolutionSource(Integer id, Integer solutionId, String source) {
        this.id = id;
        this.solutionId = solutionId;
        this.source = source;
    }

    public SolutionSource() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Integer solutionId) {
        this.solutionId = solutionId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}