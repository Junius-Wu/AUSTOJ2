package cn.edu.aust.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import cn.edu.aust.pojo.entity.SolutionDO;
import tk.mybatis.mapper.common.Mapper;

public interface SolutionMapper extends Mapper<SolutionDO> {
  /**
   * 根据条件查询用户所通过的题目
   * @param problemId 题目id
   * @param verdict 状态
   * @param userId 用户id
   * @return 提交主键
   */
  List<Long> queryIdByUserId(@Param("problemId") Long problemId,@Param("verdict") Integer verdict,
      @Param("userId") Long userId);

  /**
   * 查询用户提交列表
   * @param search 搜索内容
   * @param userId 用户id
   * @return 查询集合
   */
  List<SolutionDO> queryDetailByUserId(@Param("search") String search,@Param("userId") Long userId);

  /**
   * 根据用户id查询其最近AC的题目数
   * @param userId 用户id
   * @return 题目id
   */
  List<Integer> queryACProblems(Long userId);
}