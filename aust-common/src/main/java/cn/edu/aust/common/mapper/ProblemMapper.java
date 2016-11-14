package cn.edu.aust.common.mapper;


import java.util.List;

import cn.edu.aust.common.entity.Problem;
import cn.edu.aust.common.entity.ProblemWithBLOBs;
import cn.edu.aust.common.entity.pojo.ProblemUser;
import cn.edu.aust.common.mybatis.QueryParams;
import cn.edu.aust.common.util.PageRequest;

public interface ProblemMapper {
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
     * 根据QueryParam参数查询,动态拼接sql
     * @param queryParams 查询参数
     * @return 查询结果
     */
    List<Problem> selectParam(QueryParams queryParams);

    /**
     * 关联查询题目详情和用户
     * @param id 题目id
     * @return 题目用户包装类
     */
    ProblemUser selectProblemBlobUserByPk(Integer id);

    /**
     * 查询题目基本属性
     * @param id 题目id
     * @return 查询结果
     */
    Problem selectBaseByPk(Integer id);
}