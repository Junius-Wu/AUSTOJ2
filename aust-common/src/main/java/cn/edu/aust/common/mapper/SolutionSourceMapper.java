package cn.edu.aust.common.mapper;


import cn.edu.aust.common.entity.SolutionSource;

public interface SolutionSourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SolutionSource record);

    int insertSelective(SolutionSource record);

    SolutionSource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SolutionSource record);

    int updateByPrimaryKeyWithBLOBs(SolutionSource record);

    int updateByPrimaryKey(SolutionSource record);
}