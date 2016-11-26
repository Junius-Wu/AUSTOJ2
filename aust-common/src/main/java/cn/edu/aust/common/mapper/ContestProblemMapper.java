package cn.edu.aust.common.mapper;

import java.util.List;

import cn.edu.aust.common.entity.ContestProblem;

public interface ContestProblemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ContestProblem record);

    int insertSelective(ContestProblem record);

    ContestProblem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ContestProblem record);

    int updateByPrimaryKey(ContestProblem record);

    /**
     * 按照竞赛id查找出相应的题目
     * @param contest_id 竞赛id
     * @return 数据集
     */
    List<ContestProblem> selectByContestId(Integer contest_id);
}