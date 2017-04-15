package cn.edu.aust.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 用于列表显示
 *
 * @author Niu Li
 * @date 2017/1/29
 */
@Data
@NoArgsConstructor
@ToString
public class ProblemBasicDTO {
  private Long id;
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
  private Integer stage;
  /**
   * 难度等级
   */
  private Integer difficulty;
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
}
