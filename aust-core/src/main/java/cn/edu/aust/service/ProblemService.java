package cn.edu.aust.service;


import java.util.List;

import cn.edu.aust.common.entity.Problem;
import cn.edu.aust.common.entity.ProblemWithBLOBs;
import cn.edu.aust.common.entity.pojo.ProblemUser;
import cn.edu.aust.common.util.PageRequest;

/**
 * @author Niu Li
 * @date 2016/9/6
 */
public interface ProblemService {

    int deleteByPrimaryKey(Integer id);

    int insert(ProblemWithBLOBs record);

    int insertSelective(ProblemWithBLOBs record);

    ProblemWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProblemWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ProblemWithBLOBs record);

    int updateByPrimaryKey(Problem record);

    /**
     * 根据分页请求参数查询,动态拼接sql
     * @param pageRequest bootstrap-table请求参数集合
     * @return 查询结果
     */
    List<Problem> selectWithPageRequest(PageRequest pageRequest);

    /**
     * 关联查询题目详情和用户
     * @param id 题目id
     * @return 题目用户包装类
     */
    ProblemUser selectProblemBlobUserByPk(Integer id);
}
