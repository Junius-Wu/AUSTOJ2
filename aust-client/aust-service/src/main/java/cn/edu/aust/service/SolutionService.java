package cn.edu.aust.service;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Resource;

import cn.edu.aust.mapper.SolutionMapper;
import cn.edu.aust.mapper.SolutionSourceMapper;
import cn.edu.aust.plugin.judge.JudgeClientPool;
import cn.edu.aust.plugin.judge.JudgeResultResponse;
import cn.edu.aust.pojo.entity.Problem;
import cn.edu.aust.pojo.entity.Solution;
import cn.edu.aust.pojo.entity.SolutionSource;


/**
 * @author Niu Li
 * @since 2017/3/15
 */
@Service
public class SolutionService {
  @Resource
  private SolutionMapper solutionMapper;
  @Resource
  private SolutionSourceMapper solutionSourceMapper;
  @Resource
  private ThreadPoolExecutor taskExecutor;
  @Resource
  private JudgeClientPool judgeClientPool;

  /**
   * 保存用户提交记录,并且开启一个判题线程
   *
   * @param problem 判题题目
   * @param source  用户源码
   * @param way     所用语言
   */
  @Transactional
  public void startJudger(Long userId, Problem problem, String source, String way) {
    Solution solution = new Solution();
    solution.setCreatedate(new Date());
    solution.setModifydate(solution.getCreatedate());
    solution.setCodeLength(source.getBytes().length / 1000.0);
    solution.setContestId(problem.getContestId());
    solution.setLanguage(NumberUtils.toInt(way));
    solution.setProblemId(problem.getId());
    solution.setUserId(userId);
    //这里自动写回主键
    solutionMapper.insert(solution);
    //保存判题源码
    SolutionSource solutionSource = new SolutionSource();
    solutionSource.setSource(source);
    solutionSource.setSolutionId(solution.getId());
    solutionSourceMapper.insert(solutionSource);

    taskExecutor.execute(() -> {
      judgeClientPool.execute(judgeClient -> {
        JudgeResultResponse resultResponse = judgeClient.judge(solution.getId(), problem.getId(),
            source, way, problem.getTimeLimit(), problem.getMemoryLimit());

        return false;
      });
    });
  }
}
