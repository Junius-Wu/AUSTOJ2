package cn.edu.aust.query;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

/**
 * 文章查询结果包装类,只用于接受结果集,不能用于更新,插入等操作
 *
 * @author Niu Li
 * @since  2017/1/30
 */
@Data
@ToString
public class ArticlePO{

  private Long id;

  private String title;

  /**
   * 作者昵称
   */
  private String nickname;

  private String keyword;

  private Integer viewCount;

  private Integer likeCount;

  private Integer isTop;

  private Integer isVote;

  private String summary;
  /**
   * 该字段并不是必须返回的
   */
  private String htmlContent;

  private Date createDate;

}
