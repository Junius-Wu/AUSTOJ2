package cn.edu.aust.common.mapper;

import java.util.List;

import cn.edu.aust.common.entity.Contest;
import cn.edu.aust.common.mybatis.QueryParams;

public interface ContestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Contest record);

    int insertSelective(Contest record);

    Contest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Contest record);

    int updateByPrimaryKeyWithBLOBs(Contest record);

    int updateByPrimaryKey(Contest record);

    /**
     * 根据传入参数动态查询
     * @param queryParams 传入的参数
     * @return 查询集合
     */
    List<Contest> selectParam(QueryParams queryParams);
}