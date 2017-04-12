package cn.edu.aust.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 题目详细
 *
 * @author Niu Li
 * @date 2017/1/30
 */
@Data
@NoArgsConstructor
@ToString
public class ProblemDTO {
  private Long id;
  private String title;
  private Integer timeLimit;
  private Integer memoryLimit;
  private String description;
  private String input;
  private String output;
  private String sampleInput;
  private String sampleOutput;
  private String hit;
  private String nickname;
  private Long contestId;
}
