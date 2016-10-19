package cn.edu.aust.common.mapper;

import java.util.List;

import cn.edu.aust.common.util.PageAble;
import cn.edu.aust.common.entity.Problem;
import cn.edu.aust.common.entity.ProblemBLOBs;


public interface ProblemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProblemBLOBs record);

    int insertSelective(ProblemBLOBs record);

    ProblemBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProblemBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ProblemBLOBs record);

    int updateByPrimaryKey(Problem record);

    List<Problem> selectWithCriteria(PageAble pageAble);
}