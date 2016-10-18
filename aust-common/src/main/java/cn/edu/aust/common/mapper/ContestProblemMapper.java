package cn.edu.aust.common.mapper;


import cn.edu.aust.common.entity.ContestProblem;

public interface ContestProblemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ContestProblem record);

    int insertSelective(ContestProblem record);

    ContestProblem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ContestProblem record);

    int updateByPrimaryKey(ContestProblem record);
}