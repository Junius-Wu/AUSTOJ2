package cn.edu.aust.service;

import cn.edu.aust.entity.Solution;

/**
 * @author Niu Li
 * @date 2016/9/6
 */
public interface SolutionService {

    int deleteByPrimaryKey(Integer id);

    int insert(Solution record);

    int insertSelective(Solution record);

    Solution selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Solution record);

    int updateByPrimaryKey(Solution record);
}
