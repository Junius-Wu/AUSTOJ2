package cn.edu.aust.service.impl;

import com.alibaba.fastjson.JSONObject;

import org.springframework.stereotype.Service;

import java.util.Date;

import javax.annotation.Resource;

import cn.edu.aust.common.constant.ReportLogConstant;
import cn.edu.aust.common.entity.ProblemComment;
import cn.edu.aust.common.entity.VoteLog;
import cn.edu.aust.common.mapper.ProblemCommentMapper;
import cn.edu.aust.common.mapper.VoteLogMapper;
import cn.edu.aust.service.VoteLogService;

/**
 * 实现类
 * @author Niu Li
 * @date 2016/11/15
 */
@Service("voteLogServiceImpl")
public class VoteLogServiceImpl implements VoteLogService {
    @Resource
    private VoteLogMapper voteLogMapper;
    @Resource
    private ProblemCommentMapper problemCommentMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return voteLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(VoteLog record) {
        return voteLogMapper.insert(record);
    }

    @Override
    public int insertSelective(VoteLog record) {
        return voteLogMapper.insertSelective(record);
    }

    @Override
    public VoteLog selectByPrimaryKey(Integer id) {
        return voteLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(VoteLog record) {
        return voteLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(VoteLog record) {
        return voteLogMapper.updateByPrimaryKey(record);
    }

    @Override
    public JSONObject voteProblemComment(JSONObject result, Integer comment_id, Integer user_id,Integer status) {
        VoteLog voteLog = voteLogMapper.selectByType(ReportLogConstant.TYPE_PRO_COMMENT,comment_id, user_id);
        if (voteLog == null){
            voteLog = new VoteLog();
            voteLog.setType(ReportLogConstant.TYPE_PRO_COMMENT);
            voteLog.setStatus((byte)0);
            voteLog.setOtherId(comment_id);
            voteLog.setUserId(user_id);
            voteLog.setCreatetime(new Date());
            voteLogMapper.insertSelective(voteLog);//这里需要设置插入并返回主键keyProperty="id"
        }
        int likeCount = 0;
        if (status == 1){
            if (voteLog.getStatus()==-1) likeCount=1;
            voteLog.setStatus((byte)(voteLog.getStatus()==1?0:1));
            if (voteLog.getStatus() == 0){
                likeCount += -1;
            }else {
                likeCount += 1;
            }
        }else {
            if (voteLog.getStatus()==1) likeCount=-1;
            voteLog.setStatus((byte)(voteLog.getStatus()==1 || voteLog.getStatus()==0?-1: 0));
            if (voteLog.getStatus() == 0){
                likeCount += 1;
            }else {
                likeCount += -1;
            }
        }
        voteLogMapper.updateByPrimaryKey(voteLog);
        ProblemComment problemComment = problemCommentMapper.selectByPrimaryKey(comment_id);
        problemComment.setLikecount(problemComment.getLikecount()+likeCount);
        problemCommentMapper.updateByPrimaryKeySelective(problemComment);
        result.put("com-status",voteLog.getStatus());
        result.put("count",problemComment.getLikecount());
        return result;
    }


}
