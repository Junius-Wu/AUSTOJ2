package cn.edu.aust.vo;

import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import cn.edu.aust.pojo.entity.ArticleDO;
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
  private Integer likecount;
  private Integer isTop;
  private Integer year;
  private Integer month;
  private Integer day;
  private Integer isVote;
  private String nickname;
  private String summary;

  public static ArticleTableVO assembler(ArticleDO articlePO) {
    ArticleTableVO tableVO = new ArticleTableVO();
    tableVO.setId(articlePO.getId());
    tableVO.setTitle(articlePO.getTitle());
    tableVO.setKeyword(articlePO.getKeyword());
    tableVO.setViewcount(articlePO.getViewCount());
    tableVO.setLikecount(articlePO.getLikeCount());
    tableVO.setIsTop(articlePO.getIsTop());
    Date createDate = articlePO.getCreatedate();
    LocalDate date = LocalDate.from(createDate.toInstant().atZone(ZoneId.systemDefault()));
    tableVO.setYear(date.getYear());
    tableVO.setMonth(date.getMonthValue());
    tableVO.setDay(date.getDayOfMonth());
    tableVO.setNickname(articlePO.getAuthorName());
    tableVO.setSummary(articlePO.getSummary());
    return tableVO;
  }

  public static List<ArticleTableVO> assembler(List<ArticleDO> articlePOS,Set<Long> voteIds){
    if (CollectionUtils.isEmpty(articlePOS)) {
      return Collections.emptyList();
    }
    return articlePOS.stream().map(ArticleTableVO::assembler)
        .map(x -> {
          if (voteIds.contains(x.getId())){
            x.setIsVote(1);
          }
          return x;
        })
        .collect(Collectors.toList());
  }

}
