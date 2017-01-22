package cn.edu.aust.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 举报记录的service
 * @author Niu Li
 * @date 2016/11/15
 */
public interface ReportLogService {
    int deleteByPrimaryKey(Integer id);

    int insert(ReportLog record);

    int insertSelective(ReportLog record);

    ReportLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReportLog record);

    int updateByPrimaryKey(ReportLog record);

    /**
     * 举报题目评论(若未举报过则创建新记录,举报过则对状态取反返回)
     * @param result 要包装的返回结果
     * @param comment_id 题目评论id
     * @param user_id 用户id
     * @return 包装后的值
     */
    JSONObject reportProblemComment(JSONObject result, Integer comment_id, Integer user_id);
}
