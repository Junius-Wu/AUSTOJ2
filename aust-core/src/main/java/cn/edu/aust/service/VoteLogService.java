package cn.edu.aust.service;

import com.alibaba.fastjson.JSONObject;

import cn.edu.aust.common.entity.Article;
import cn.edu.aust.common.entity.VoteLog;

/**
 * 点赞记录的service
 * @author Niu Li
 * @date 2016/11/15
 */
public interface VoteLogService {
    int deleteByPrimaryKey(Integer id);

    int insert(VoteLog record);

    int insertSelective(VoteLog record);

    VoteLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VoteLog record);

    int updateByPrimaryKey(VoteLog record);

    /**
     * 点赞题目评论(若未点赞过则创建新记录,点赞过则对状态取反返回)
     * @param result 要包装的返回结果
     * @param comment_id 题目评论id
     * @param user_id 用户id
     * @param status 标识点赞还是踩
     * @return 包装后的值
     */
    JSONObject voteProblemComment(JSONObject result, Integer comment_id, Integer user_id,Integer status);

    /**
     * 文章点赞(若未点赞过则创建新记录,点赞过则对状态取反返回)
     * @param result 要包装的返回结果
     * @param article 该文章
     * @param user_id 当前用户id
     * @return 结果
     */
    JSONObject voteArticleComment(JSONObject result, Article article, Integer user_id);

}
