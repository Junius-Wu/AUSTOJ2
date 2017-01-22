package cn.edu.aust.service;

import com.alibaba.fastjson.JSONObject;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import cn.edu.aust.common.mapper.ContestMapper;
import cn.edu.aust.common.mybatis.Filter;
import cn.edu.aust.common.mybatis.Order;
import cn.edu.aust.common.mybatis.QueryParams;
import cn.edu.aust.common.util.ReturnUtil;
import cn.edu.aust.service.ContestService;

/**
 * 竞赛服务
 * @author Niu Li
 * @date 2016/11/26
 */
@Service("contestServiceImpl")
public class ContestServiceImpl implements ContestService{
    @Resource
    private ContestMapper contestMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return contestMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Contest record) {
        return contestMapper.insert(record);
    }

    @Override
    public int insertSelective(Contest record) {
        return contestMapper.insertSelective(record);
    }

    @Override
    public Contest selectByPrimaryKey(Integer id) {
        return contestMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Contest record) {
        return contestMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(Contest record) {
        return contestMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(Contest record) {
        return contestMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Contest> selectDefunct(boolean isOverTime) {
        QueryParams queryParams = new QueryParams();
        Date startDate = new Date();
        if (!isOverTime){
            queryParams.and(Filter.eq("defunct",(byte)1))
                       .and(Filter.lt("start_time", startDate))
                       .and(Filter.gt("end_time",startDate))
                       .order(Order.descOrder("start_time"));
        }else {
            queryParams.and(Filter.le("end_time",startDate))
                       .order(Order.descOrder("end_time"));
        }
        return contestMapper.selectParam(queryParams);
    }

    @Override
    public boolean judgeCanAccess(Integer id,String passwd, JSONObject result) {
        Contest contest = Optional.of(contestMapper.selectByPrimaryKey(id))
                                  //检查时间
                                  .filter(contest1 -> {
                                      if (contest1.getStartTime().after(new Date())) {
                                          ReturnUtil.packingRes(result, ResultVo.CONTEST_NOSTART);
                                          return false;
                                      }
                                      return true;
                                  })
                                  //检查密码
                                  .filter(contest1 -> {
                                      if (contest1.getType() == 1 && !contest1.getPassword().equals(passwd)){
                                          ReturnUtil.packingRes(result, ResultVo.CONTEST_PASSWD);
                                          return false;
                                      }
                                      return true;
                                  }).orElse(null);
        return contest!=null;
    }
}
