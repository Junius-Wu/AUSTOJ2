package cn.edu.aust.dto;

import java.util.Date;

import javax.persistence.Column;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/** 不同于ArticleDO,此实体中只包含基础字段
 * @author Niu Li
 * @since 2017/4/28
 */
@Data
@NoArgsConstructor
@ToString
public class BaseArticleDTO {
  /**
   * 主键
   */
  private Long id;
  /**
   * 摘要
   */
  private String summary;
  /**
   * 标题
   */
  private String title;
  /**
   * 用户id
   */
  @Column(name = "author_id")
  private Integer authorId;

  @Column(name = "author_name")
  private String authorName;
  /**
   * 关键词,都好分隔
   */
  private String keyword;
  /**
   * 阅读数
   */
  @Column(name = "view_count")
  private Integer viewCount;
  /**
   * 点赞数
   */
  @Column(name = "like_count")
  private Integer likeCount;

  /**
   * 目录
   */
  @Column(name = "catelog_id")
  private Integer catelogId;

  /**
   * 0不置顶,1置顶
   */
  @Column(name = "is_top")
  private Integer isTop;

  /**
   * 0不展示,1展示
   */
  @Column(name = "is_show")
  private Integer isShow;

  private Date createdate;

  private Date modifydate;
}
