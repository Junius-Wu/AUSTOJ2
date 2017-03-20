package cn.edu.aust.service;

import com.alibaba.fastjson.JSONObject;

import org.springframework.stereotype.Service;

import java.util.Date;

import javax.annotation.Resource;

import cn.edu.aust.dto.ArticleDTO;
import cn.edu.aust.mapper.ArticleMapper;
import cn.edu.aust.mapper.VotelogMapper;
import cn.edu.aust.pojo.entity.ArticleDO;
import cn.edu.aust.pojo.entity.VotelogDO;

/**
 * 点赞关系表
 * @author Niu Li
 * @date 2017/1/30
 */
@Service
public class VotelogService {
    @Resource
    private VotelogMapper votelogMapper;
    @Resource
    private ArticleMapper articleMapper;

    /**
     * 文章点赞
     * @param result 写回结果
     * @param articleDTO 对应文章
     * @param user_id 对应用户id
     * @return 写回结果
     */
    public JSONObject voteArticleComment(JSONObject result, ArticleDTO articleDTO, Long user_id) {
        ArticleDO articleDO = articleMapper.selectByPrimaryKey(articleDTO.getId());
        VotelogDO voteLog = new VotelogDO();
        voteLog.setUserId(user_id);
        voteLog.setOtherId(articleDO.getId());
        voteLog.setType((byte) 2);
        voteLog = votelogMapper.selectOne(voteLog);
        if (voteLog == null){
            voteLog = new VotelogDO();
            voteLog.setType((byte) 2);
            voteLog.setStatus((byte)1);
            voteLog.setOtherId(articleDO.getId());
            voteLog.setUserId(user_id);
            voteLog.setCreatetime(new Date());
            votelogMapper.insert(voteLog);//这里需要设置插入并返回主键keyProperty="id"
        }else {
            voteLog.setStatus((byte)(voteLog.getStatus()^1));
            votelogMapper.updateByPrimaryKeySelective(voteLog);
        }
        ArticleDO tempArticleDO = new ArticleDO();
        tempArticleDO.setId(articleDO.getId());
        tempArticleDO.setLikecount(articleDO.getLikecount()+(voteLog.getStatus()==(byte)0?-1:1));
        articleMapper.updateByPrimaryKeySelective(tempArticleDO);
        result.put("art_status",voteLog.getStatus());
        result.put("count", tempArticleDO.getLikecount());
        return result;
    }
}
