package cn.edu.aust.mapper;

import java.util.List;

import cn.edu.aust.pojo.entity.Problem;
import cn.edu.aust.query.ProblemPC;
import cn.edu.aust.query.ProblemQM;
import tk.mybatis.mapper.common.Mapper;

public interface ProblemMapper extends Mapper<Problem> {
    /**
     * 查询出指定阶段的所有题目
     * @param problemQM 查询实体
     * @return 查询集合包装类
     */
    List<ProblemPC> queryListStage(ProblemQM problemQM);
}