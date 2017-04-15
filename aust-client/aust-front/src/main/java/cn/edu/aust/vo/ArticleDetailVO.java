package cn.edu.aust.vo;

import cn.edu.aust.common.util.DateUtil;
import cn.edu.aust.dto.ArticleDTO;
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
  private String nickname;
  //内容
  private String htmlContent;


  public static ArticleDetailVO assembler(ArticleDTO articleDTO) {
    ArticleDetailVO detailVO = new ArticleDetailVO();
    detailVO.setId(articleDTO.getId());
    detailVO.setTitle(articleDTO.getTitle());
    detailVO.setKeyword(articleDTO.getKeyword());
    detailVO.setViewcount(articleDTO.getViewCount());
    detailVO.setLikecount(articleDTO.getLikeCount());
    detailVO.setCreateDate(DateUtil.format(articleDTO.getCreateDate(),DateUtil.YMDHMS_));
    detailVO.setNickname(articleDTO.getNickName());
    detailVO.setHtmlContent(articleDTO.getHtmlContent());
    return detailVO;
  }

}
