package cn.edu.aust.mapper;

import cn.edu.aust.entity.Contest;

public interface ContestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Contest record);

    int insertSelective(Contest record);

    Contest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Contest record);

    int updateByPrimaryKeyWithBLOBs(Contest record);

    int updateByPrimaryKey(Contest record);
}