package cn.edu.aust.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import cn.edu.aust.mapper.ContestProblemMapper;
import cn.edu.aust.pojo.entity.ContestProblemDO;
import lombok.extern.slf4j.Slf4j;

/**
 * 竞赛题目服务
 * @author Niu Li
 * @since 2017/4/28
 */
@Slf4j
@Service
public class ContestProblemService {

  @Resource
  private ContestProblemMapper contestProblemMapper;
  @Resource
  private ContestService contestService;
  @Resource
  private StringRedisTemplate redisTemplate;

  /**
   * 查询竞赛题目集合
   * @param contestId 竞赛id
   * @return 查询集合
   */
  public List<ContestProblemDO> queryByContest(Long contestId){
    if (Objects.isNull(contestId)) {
      return Collections.emptyList();
    }
    return contestProblemMapper.queryByContest(contestId);
  }

  /**
   * 判断竞赛中是否含有题目
   * @param contestId 竞赛id
   * @param problemId 题目id
   * @return true含有
   */
  public Boolean isContainProblem(Long contestId,Long problemId){
    List<ContestProblemDO> problemDOS = queryByContest(contestId);
    if (CollectionUtils.isEmpty(problemDOS)) {
      return false;
    }
    return problemDOS.contains(problemDOS);
  }

}
