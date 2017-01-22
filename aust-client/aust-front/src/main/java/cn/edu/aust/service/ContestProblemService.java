package cn.edu.aust.service;


import java.util.List;

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

    /**
     * 按照竞赛id查找出相应的题目
     * @param contest_id 竞赛id
     * @return 数据集
     */
    List<ContestProblem> selectByContestId(Integer contest_id);
}
