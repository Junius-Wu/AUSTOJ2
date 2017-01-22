package cn.edu.aust.service;


import com.alibaba.fastjson.JSONObject;

import java.util.List;

import cn.edu.aust.common.entity.Contest;

/**
 * @author Niu Li
 * @date 2016/9/6
 */
public interface ContestService {
    int deleteByPrimaryKey(Integer id);

    int insert(Contest record);

    int insertSelective(Contest record);

    Contest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Contest record);

    int updateByPrimaryKeyWithBLOBs(Contest record);

    int updateByPrimaryKey(Contest record);

    /**
     * 查询出有效或无效比赛
     * @param isOverTime true有效 false无效
     * @return 数据集
     */
    List<Contest> selectDefunct(boolean isOverTime);

    /**
     * 判断当前竞赛是否可以访问
     * @param id 要判断的id
     * @return true可访问
     */
    boolean judgeCanAccess(Integer id,String passwd, JSONObject result);
}
