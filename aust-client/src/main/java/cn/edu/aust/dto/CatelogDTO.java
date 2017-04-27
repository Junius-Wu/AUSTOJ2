package cn.edu.aust.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 题目目录
 * @author Niu Li
 * @since 2017/3/20
 */
@Data
@NoArgsConstructor
@ToString
public class CatelogDTO {

  private Integer id;

  private String name;

  /**
   * 0题目分类
   */
  private Integer type;
}
