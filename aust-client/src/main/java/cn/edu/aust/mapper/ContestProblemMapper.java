package cn.edu.aust.mapper;

import java.util.List;

import cn.edu.aust.pojo.entity.ContestProblemDO;
import tk.mybatis.mapper.common.Mapper;

public interface ContestProblemMapper extends Mapper<ContestProblemDO> {

  /**
   * 查询竞赛下的题目
   * @param contestId 竞赛id
   * @return 题目实体,只适用于列表显示
   */
  List<ContestProblemDO> queryByContest(Long contestId);
}