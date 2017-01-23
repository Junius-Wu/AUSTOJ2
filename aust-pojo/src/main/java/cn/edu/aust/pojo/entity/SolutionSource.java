package cn.edu.aust.pojo.entity;

import javax.persistence.*;

@Table(name = "solution_source")
public class SolutionSource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "solution_id")
    private Integer solutionId;

    private String source;

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
     * @return solution_id
     */
    public Integer getSolutionId() {
        return solutionId;
    }

    /**
     * @param solutionId
     */
    public void setSolutionId(Integer solutionId) {
        this.solutionId = solutionId;
    }

    /**
     * @return source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source
     */
    public void setSource(String source) {
        this.source = source;
    }
}