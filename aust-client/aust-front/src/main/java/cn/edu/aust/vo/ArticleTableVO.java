package cn.edu.aust.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章列表实体
 * @author Niu Li
 * @since 2017/4/14
 */
@Data
@NoArgsConstructor
public class ArticleTableVO {

  private Long id;
  private String title;
  private String keyword;
  private Integer viewcount;
  private String likecount;
  private Byte isTop;
  private Date createdate;
  private Byte isVote;
  private String nickname;
  private String summary;

}
