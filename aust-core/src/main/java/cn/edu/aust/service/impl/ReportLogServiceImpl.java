package cn.edu.aust.service.impl;

import com.alibaba.fastjson.JSONObject;

import org.springframework.stereotype.Service;

import java.util.Date;

import javax.annotation.Resource;

import cn.edu.aust.common.constant.ReportLogConstant;
import cn.edu.aust.common.entity.ReportLog;
import cn.edu.aust.common.mapper.ReportLogMapper;
import cn.edu.aust.service.ReportLogService;

/**
 * 实现类
 * @author Niu Li
 * @date 2016/11/15
 */
@Service("reportLogServiceImpl")
public class ReportLogServiceImpl implements ReportLogService{
    @Resource
    private ReportLogMapper reportLogMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return reportLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ReportLog record) {
        return reportLogMapper.insert(record);
    }

    @Override
    public int insertSelective(ReportLog record) {
        return reportLogMapper.insertSelective(record);
    }

    @Override
    public ReportLog selectByPrimaryKey(Integer id) {
        return reportLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ReportLog record) {
        return reportLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ReportLog record) {
        return reportLogMapper.updateByPrimaryKey(record);
    }

    @Override
    public JSONObject reportProblemComment(JSONObject result, Integer comment_id, Integer user_id) {
        ReportLog reportLog = reportLogMapper.selectByType(ReportLogConstant.TYPE_PRO_COMMENT,comment_id, user_id);
        if (reportLog == null){
            reportLog = new ReportLog();
            reportLog.setType(ReportLogConstant.TYPE_PRO_COMMENT);
            reportLog.setStatus((byte)1);
            reportLog.setOtherId(comment_id);
            reportLog.setUserId(user_id);
            reportLog.setCreatedate(new Date());
            reportLogMapper.insertSelective(reportLog);
        }
        result.put("reportstatus",0);
        if (reportLog.getStatus() == 1){
            return result;
        }
        reportLog.setStatus((byte)(1));
        reportLogMapper.updateByPrimaryKey(reportLog);
        return result;
    }

}
