package cn.edu.aust.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 站点公告
 * @author Niu Li
 * @since 2017/4/8
 */
@Data
@NoArgsConstructor
@ToString
public class NotifyDTO {

  private Integer id;

  /**
   * 标题
   */
  private String title;

  /**
   * 所关联文章
   */
  private Integer articleId;
}
