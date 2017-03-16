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
import cn.edu.aust.pojo.entity.ProblemDO;
import cn.edu.aust.pojo.entity.SolutionDO;
import cn.edu.aust.pojo.entity.SolutionSourceDO;
import lombok.extern.slf4j.Slf4j;


/**
 * @author Niu Li
 * @since 2017/3/15
 */
@Service
@Slf4j
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
   * @param problemDO 判题题目
   * @param source  用户源码
   * @param way     所用语言
   */
  @Transactional
  public void startJudger(Long userId, ProblemDO problemDO, String source, String way) {
    SolutionDO solutionDO = new SolutionDO();
    solutionDO.setCreatedate(new Date());
    solutionDO.setModifydate(solutionDO.getCreatedate());
    solutionDO.setCodeLength(source.getBytes().length / 1000.0);
    solutionDO.setContestId(problemDO.getContestId());
    solutionDO.setLanguage(NumberUtils.toInt(way));
    solutionDO.setProblemId(problemDO.getId());
    solutionDO.setUserId(userId);
    //这里自动写回主键
    solutionMapper.insert(solutionDO);
    //保存判题源码
    SolutionSourceDO solutionSourceDO = new SolutionSourceDO();
    solutionSourceDO.setSource(source);
    solutionSourceDO.setSolutionId(solutionDO.getId());
    solutionSourceMapper.insert(solutionSourceDO);

    taskExecutor.execute(() -> {
      judgeClientPool.execute(judgeClient -> {
        JudgeResultResponse resultResponse = judgeClient.judge(solutionDO.getId(), problemDO.getId(),
            source, way, problemDO.getTimeLimit(), problemDO.getMemoryLimit());

        return false;
      });
    });
  }
}
