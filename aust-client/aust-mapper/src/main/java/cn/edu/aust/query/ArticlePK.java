package cn.edu.aust.query;

import cn.edu.aust.pojo.entity.ArticleDO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 文章查询结果包装类
 *
 * @author Niu Li
 * @date 2017/1/30
 */
@Data
@NoArgsConstructor
@ToString
public class ArticlePK extends ArticleDO {
  /**
   * 作者昵称
   */
  private String nickname;

  private Integer isVote;
}
