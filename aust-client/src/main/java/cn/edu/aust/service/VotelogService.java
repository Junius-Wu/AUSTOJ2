package cn.edu.aust.service;

import org.springframework.stereotype.Service;

import java.util.Date;

import javax.annotation.Resource;

import cn.edu.aust.dto.BaseArticleDTO;
import cn.edu.aust.dto.VoteStatusDTO;
import cn.edu.aust.mapper.ArticleMapper;
import cn.edu.aust.mapper.VotelogMapper;
import cn.edu.aust.pojo.entity.ArticleDO;
import cn.edu.aust.pojo.entity.VotelogDO;

/**
 * 点赞关系表
 *
 * @author Niu Li
 * @since 2017/1/30
 */
@Service
public class VotelogService {
  @Resource
  private VotelogMapper votelogMapper;
  @Resource
  private ArticleMapper articleMapper;

  /**
   * 文章点赞
   *
   * @param articleDTO 对应文章
   * @param user_id    对应用户id
   * @return 写回结果
   */
  public VoteStatusDTO voteArticleComment(BaseArticleDTO articleDTO, Long user_id) {
    ArticleDO articleDO = articleMapper.selectByPrimaryKey(articleDTO.getId());
    VotelogDO voteLog = new VotelogDO();
    voteLog.setUserId(user_id);
    voteLog.setOtherId(articleDO.getId());
    voteLog.setType((byte) 2);
    voteLog = votelogMapper.selectOne(voteLog);
    if (voteLog == null) {
      voteLog = new VotelogDO();
      voteLog.setType((byte) 2);
      voteLog.setStatus((byte) 1);
      voteLog.setOtherId(articleDO.getId());
      voteLog.setUserId(user_id);
      voteLog.setCreatetime(new Date());
      votelogMapper.insert(voteLog);
    } else {
      voteLog.setStatus((byte) (voteLog.getStatus() ^ 1));
      votelogMapper.updateByPrimaryKeySelective(voteLog);
    }
    ArticleDO tempArticleDO = new ArticleDO();
    tempArticleDO.setId(articleDO.getId());
    tempArticleDO.setLikeCount(articleDO.getLikeCount() + (voteLog.getStatus() == (byte) 0 ? -1 : 1));
    articleMapper.updateByPrimaryKeySelective(tempArticleDO);

    VoteStatusDTO statusDTO = new VoteStatusDTO();
    statusDTO.setCount(tempArticleDO.getLikeCount());
    statusDTO.setCount(Integer.valueOf(voteLog.getStatus()));
    return statusDTO;
  }
}
