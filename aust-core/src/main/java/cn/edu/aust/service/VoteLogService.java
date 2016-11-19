package cn.edu.aust.service;

import com.alibaba.fastjson.JSONObject;

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
     * 举报题目评论(若未举报过则创建新记录,举报过则对状态取反返回)
     * @param result 要包装的返回结果
     * @param comment_id 题目评论id
     * @param user_id 用户id
     * @param status 标识点赞还是踩
     * @return 包装后的值
     */
    JSONObject voteProblemComment(JSONObject result, Integer comment_id, Integer user_id,Integer status);

}
