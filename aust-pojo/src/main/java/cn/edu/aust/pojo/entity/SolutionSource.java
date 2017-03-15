package cn.edu.aust.pojo.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "solution_source")
@Data
@NoArgsConstructor
public class SolutionSource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "solution_id")
    private Long solutionId;

    private String source;

}