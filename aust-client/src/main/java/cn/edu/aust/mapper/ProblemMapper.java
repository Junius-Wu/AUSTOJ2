package cn.edu.aust.mapper;

import java.util.List;

import cn.edu.aust.entity.ProblemQuery;
import cn.edu.aust.pojo.entity.ProblemDO;
import tk.mybatis.mapper.common.Mapper;

public interface ProblemMapper extends Mapper<ProblemDO> {
    /**
     * 查询出指定阶段的所有题目
     * @param problemQuery 查询实体
     * @return 查询集合包装类
     */
    List<ProblemDO> queryListStage(ProblemQuery problemQuery);
    /**
     * 查询出指定目录的所有题目
     * @param problemQuery 查询实体
     * @return 查询集合包装类
     */
    List<ProblemDO> queryListCatelog(ProblemQuery problemQuery);

    /**
     * 根据主键查询一个题目的详细
     * @param id 主键
     * @return 结果
     */
    ProblemDO findDetail(Long id);

    /**
     * 查询题目基本信息
     * @param id 主键
     * @return 题目
     */
    ProblemDO findBasic(Long id);


}