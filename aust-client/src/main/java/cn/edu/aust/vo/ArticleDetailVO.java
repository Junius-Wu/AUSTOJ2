package cn.edu.aust.vo;

import cn.edu.aust.common.util.DateUtil;
import cn.edu.aust.pojo.entity.ArticleDO;
import lombok.Data;

/**
 * 文章展示详情类
 * @author Niu Li
 * @since 2017/4/15
 */
@Data
public class ArticleDetailVO {

  private Long id;
  private String title;
  private String keyword;
  private Integer viewcount;
  private Integer likecount;
  private Integer isTop;
  private String createDate;
  private Integer isVote;
  private String authorName;
  //内容
  private String htmlContent;


  public static ArticleDetailVO assembler(ArticleDO article) {
    ArticleDetailVO detailVO = new ArticleDetailVO();
    detailVO.setId(article.getId());
    detailVO.setTitle(article.getTitle());
    detailVO.setKeyword(article.getKeyword());
    detailVO.setViewcount(article.getViewCount());
    detailVO.setLikecount(article.getLikeCount());
    detailVO.setCreateDate(DateUtil.format(article.getCreatedate(),DateUtil.YMDHMS_));
    detailVO.setAuthorName(article.getAuthorName());
    detailVO.setHtmlContent(article.getHtmlContent());
    return detailVO;
  }

}
