package cn.edu.aust.dto;

import cn.edu.aust.common.constant.ProblemType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * problem基础字段
 * @author Niu Li
 * @since 2017/4/27
 */
@Data
@NoArgsConstructor
@ToString
public class ProblemBasicDTO {
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
  private ProblemType type;
  private Integer catelogId;
}
