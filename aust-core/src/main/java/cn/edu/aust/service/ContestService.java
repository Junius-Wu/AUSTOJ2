package cn.edu.aust.service;


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
}
