package cn.edu.aust.mapper;

import cn.edu.aust.entity.Problem;
import cn.edu.aust.entity.ProblemBLOBs;

public interface ProblemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProblemBLOBs record);

    int insertSelective(ProblemBLOBs record);

    ProblemBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProblemBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ProblemBLOBs record);

    int updateByPrimaryKey(Problem record);
}