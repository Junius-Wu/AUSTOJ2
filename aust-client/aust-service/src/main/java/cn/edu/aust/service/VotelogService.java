package cn.edu.aust.service;

import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import cn.edu.aust.mapper.VotelogMapper;
import cn.edu.aust.pojo.entity.Article;
import cn.edu.aust.pojo.entity.Votelog;

/**
 * 点赞关系表
 * @author Niu Li
 * @date 2017/1/30
 */
@Service
public class VotelogService extends BaseService<Votelog>{
    @Autowired
    private VotelogMapper votelogMapper;
    @Autowired
    private ArticleService articleService;

    /**
     * 文章点赞
     * @param result 写回结果
     * @param article 对应文章
     * @param user_id 对应用户id
     * @return 写回结果
     */
    public JSONObject voteArticleComment(JSONObject result, Article article, Long user_id) {
        Votelog voteLog = new Votelog();
        voteLog.setUserId(user_id);
        voteLog.setOtherId(article.getId());
        voteLog.setType((byte) 2);
        voteLog = votelogMapper.selectOne(voteLog);
        if (voteLog == null){
            voteLog = new Votelog();
            voteLog.setType((byte) 2);
            voteLog.setStatus((byte)1);
            voteLog.setOtherId(article.getId());
            voteLog.setUserId(user_id);
            voteLog.setCreatetime(new Date());
            save(voteLog);//这里需要设置插入并返回主键keyProperty="id"
        }else {
            voteLog.setStatus((byte)(voteLog.getStatus()^1));
            updateSelective(voteLog);
        }
        Article tempArticle = new Article();
        tempArticle.setId(article.getId());
        tempArticle.setLikecount(article.getLikecount()+(voteLog.getStatus()==(byte)0?-1:1));
        articleService.updateSelective(tempArticle);
        result.put("art_status",voteLog.getStatus());
        result.put("count",tempArticle.getLikecount());
        return result;
    }
}
