package cn.edu.aust.pojo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "article")
@Data
@NoArgsConstructor
@ToString
public class ArticleDO {
  /**
   * 主键
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  /**
   * 摘要
   */
  private String summary;
  /**
   * html内容
   */
  @Column(name = "html_content")
  private String htmlContent;
  /**
   * markdown内容
   */
  private String content;
  /**
   * 标题
   */
  private String title;

  /**
   * 用户id
   */
  @Column(name = "user_id")
  private Integer userId;

  /**
   * 关键词,都好分隔
   */
  private String keyword;

  /**
   * 阅读数
   */
  private Integer viewcount;

  /**
   * 点赞数
   */
  private Integer likecount;

  /**
   * 暂且废除字段
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