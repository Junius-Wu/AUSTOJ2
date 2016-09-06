package cn.edu.aust.service;

import cn.edu.aust.entity.ContestProblem;

/**
 * @author Niu Li
 * @date 2016/9/6
 */
public interface ContestProblemService {
    int deleteByPrimaryKey(Integer id);

    int insert(ContestProblem record);

    int insertSelective(ContestProblem record);

    ContestProblem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ContestProblem record);

    int updateByPrimaryKey(ContestProblem record);
}
