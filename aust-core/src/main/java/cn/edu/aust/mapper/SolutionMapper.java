package cn.edu.aust.mapper;

import cn.edu.aust.entity.Solution;

public interface SolutionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Solution record);

    int insertSelective(Solution record);

    Solution selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Solution record);

    int updateByPrimaryKey(Solution record);
}