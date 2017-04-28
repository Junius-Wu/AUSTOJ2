package cn.edu.aust.dto;

import java.util.Date;

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
public class BaseProblemDTO {
  private Long id;
  private String title;
  private String keyword;
  private Integer catelog;
  private Integer stage;
  private Integer timeLimit;
  private Integer memoryLimit;
  private Integer difficulty;
  private Integer solved;
  private Integer submit;
  private String description;
  private String input;
  private String output;
  private String sampleInput;
  private String sampleOutput;
  private String hit;
  private Integer authorId;
  private String authorName;
  private Integer type;
  private Date createdate;
  private Date modifydate;
}
