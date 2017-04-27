package cn.edu.aust.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.edu.aust.common.constant.JudgeCode;
import cn.edu.aust.common.constant.MessageKey;
import cn.edu.aust.common.entity.MessageType;
import cn.edu.aust.common.util.LanguageUtil;
import cn.edu.aust.convert.SolutionConvert;
import cn.edu.aust.dto.SolutionDTO;
import cn.edu.aust.mapper.ProblemMapper;
import cn.edu.aust.mapper.SolutionMapper;
import cn.edu.aust.mapper.SolutionSourceMapper;
import cn.edu.aust.mapper.UserMapper;
import cn.edu.aust.plugin.judge.JudgeClientPool;
import cn.edu.aust.plugin.judge.JudgeResultResponse;
import cn.edu.aust.pojo.entity.ProblemDO;
import cn.edu.aust.pojo.entity.SolutionDO;
import cn.edu.aust.pojo.entity.SolutionSourceDO;
import cn.edu.aust.pojo.entity.UserDO;
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
  private UserMapper userMapper;
  @Resource
  private ProblemMapper problemMapper;
  @Resource
  private ThreadPoolTaskExecutor taskExecutor;
  @Resource
  private JudgeClientPool judgeClientPool;
  @Resource
  private StringRedisTemplate redisTemplate;

  /**
   * 查询用户提交列表
   *
   * @param searchContext 搜索内容
   * @param userId        用户id
   * @return 查询集合
   */
  public PageInfo<SolutionDTO> userSolutionList(String searchContext, Long userId, Integer pageNum,
      Integer pageSize) {
    if (StringUtils.isNotEmpty(searchContext)) {
      searchContext = "%" + searchContext + "%";
    }
    PageHelper.startPage(pageNum, pageSize);
    List<SolutionDO> queryresult = solutionMapper.queryDetailByUserId(searchContext, userId);
    PageInfo<SolutionDTO> pageInfo = new PageInfo<>(SolutionConvert.do2dto(queryresult));
    pageInfo.setTotal(((Page) queryresult).getTotal());
    return pageInfo;
  }


  /**
   * 保存用户提交记录,并且开启一个判题线程
   *
   * @param problemDTO 判题题目
   * @param source    用户源码
   * @param language  所用语言
   */
  @Transactional(rollbackFor = Exception.class)
  public void startJudger(Long userId, ProblemDTO problemDTO, String source,
      LanguageUtil.Language language) {
    ProblemDO problemDO = problemMapper.selectByPrimaryKey(problemDTO.getId());
    SolutionDO solutionDO = new SolutionDO();
    solutionDO.setCreatedate(new Date());
    solutionDO.setModifydate(solutionDO.getCreatedate());
    solutionDO.setCodeLength(source.getBytes().length / 8.0);
//    solutionDO.setContestId(problemDO.getContestId());
    solutionDO.setLanguage(language.getLanguageName());
    solutionDO.setProblemId(problemDO.getId());
    solutionDO.setProblemTitle(problemDO.getTitle());
    solutionDO.setUserId(userId);
    solutionDO.setVerdict(JudgeCode.COMPILEING.getStatus());
    solutionDO.setTestcase(0);
    //这里自动写回主键
    solutionMapper.insert(solutionDO);
    //保存判题源码
    SolutionSourceDO solutionSourceDO = new SolutionSourceDO();
    solutionSourceDO.setSource(source);
    solutionSourceDO.setSolutionId(solutionDO.getId());
    solutionSourceMapper.insert(solutionSourceDO);
    //创建判题任务
    log.info("start judge , solutionID:{}",solutionDO.getId());
    judgeExecute(problemDO, source, language.getLanguageName(), solutionDO);
  }

  /**
   * 创建判题任务,并处理判题结果
   *
   * @param problemDO 题目
   * @param source    源码
   * @param language  语言
   * @param solution  对应判题记录
   */
  private void judgeExecute(ProblemDO problemDO, String source, String language,
      SolutionDO solution) {
    log.info("judge request solutionID:{}",solution.getId());
    taskExecutor.execute(
        judgeClientPool.execute(judgeClient -> {
          JudgeResultResponse resultResponse = judgeClient.judge(solution.getId(), problemDO.getId(),
              source, language, problemDO.getTimeLimit(), problemDO.getMemoryLimit());

          log.info("judge response solutionID:{},result:{}",solution.getId(),resultResponse);
          //更新solution
          solution.setModifydate(new Date());
          solution.setTime(resultResponse.getUseTime());
          solution.setMemory(resultResponse.getUseMemory());
          solution.setVerdict(resultResponse.getExitCode());
          solution.setTestcase(resultResponse.getTestcase());
          solutionMapper.updateByPrimaryKeySelective(solution);
          //根据返回错误码更新题目和用户信息
          dealWithByJudgeCode(resultResponse, problemDO.getId(), solution.getUserId());
          return true;
        })
    );
  }

  /**
   * 根据判题返回错误码更新用户和题目的最新信息
   *
   * @param resultResponse 判题结果
   */
  @Transactional(rollbackFor = Exception.class)
  private void dealWithByJudgeCode(JudgeResultResponse resultResponse, Long problemId, Long userId) {
    UserDO userDO = userMapper.selectByPrimaryKey(userId);
    ProblemDO problemDO = problemMapper.selectByPrimaryKey(problemId);
    //判断该题目是否被该用户AC过
    List<Long> solution = solutionMapper.queryIdByUserId(problemId, JudgeCode.AC.getStatus(), userId);
    //未被AC
    problemDO.setSubmit(problemDO.getSubmit() + 1);
    userDO.setSubmit(userDO.getSubmit() + 1);
    if (CollectionUtils.isEmpty(solution)) {
      if (resultResponse.getExitCode().equals(JudgeCode.AC.getStatus())) {
        userDO.setSolved(userDO.getSolved() + 1);
      }
    }
    if (resultResponse.getExitCode().equals(JudgeCode.AC.getStatus())) {
      problemDO.setSolved(problemDO.getSolved() + 1);
    }
    problemMapper.updateByPrimaryKeySelective(problemDO);
    userMapper.updateByPrimaryKeySelective(userDO);
    //发布redis事件
    MessageType ms = new MessageType();
    ms.setSubjectId(userId);
    ms.setSubjectId(problemId);
    ms.setType(MessageKey.JUDGER_RESULT);
    redisTemplate.convertAndSend("judger", JSON.toJSONString(ms));
  }

  /**
   * 根据用户id查询其最近AC的题目数
   * @param userId 用户id
   * @return 题目id
   */
  public List<Integer> queryACProblems(Long userId){
    List<Integer> problemIds = null;
    try {
      problemIds = solutionMapper.queryACProblems(userId);
    } catch (Exception e) {
      log.error("queryACProblems error",e);
      return Collections.emptyList();
    }
    return problemIds;
  }
}
