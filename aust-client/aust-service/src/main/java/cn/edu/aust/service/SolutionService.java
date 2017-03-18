package cn.edu.aust.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.edu.aust.assemble.SolutionAssemble;
import cn.edu.aust.common.constant.JudgeCode;
import cn.edu.aust.common.util.LanguageUtil;
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

  /**
   * 查询用户提交列表
   * @param searchContext 搜索内容
   * @param userId 用户id
   * @return 查询集合
   */
  public PageInfo<SolutionDTO> userSolutionList(String searchContext,Long userId,Integer offset,
      Integer limit){
    if (StringUtils.isNotEmpty(searchContext)) {
      searchContext = "%" + searchContext + "%";
    }
    PageHelper.offsetPage(offset,limit);
    List<SolutionDO> queryresult = solutionMapper.queryDetailByUserId(searchContext,userId);
    PageInfo<SolutionDTO> pageInfo = new PageInfo<>(SolutionAssemble.do2dto(queryresult));
    pageInfo.setTotal(((Page)queryresult).getTotal());
    return pageInfo;
  }


  /**
   * 保存用户提交记录,并且开启一个判题线程
   *
   * @param problemDO 判题题目
   * @param source  用户源码
   * @param language     所用语言
   */
  @Transactional(rollbackFor = Exception.class)
  public void startJudger(Long userId, ProblemDO problemDO, String source,
      LanguageUtil.Language language) {
    SolutionDO solutionDO = new SolutionDO();
    solutionDO.setCreatedate(new Date());
    solutionDO.setModifydate(solutionDO.getCreatedate());
    solutionDO.setCodeLength(source.getBytes().length / 1000.0);
    solutionDO.setContestId(problemDO.getContestId());
    solutionDO.setLanguage(language.getLanguageName());
    solutionDO.setProblemId(problemDO.getId());
    solutionDO.setProblemTitle(problemDO.getTitle());
    solutionDO.setUserId(userId);
    solutionDO.setVerdict(JudgeCode.COMPILEING.getStatus());
    //这里自动写回主键
    solutionMapper.insert(solutionDO);
    //保存判题源码
    SolutionSourceDO solutionSourceDO = new SolutionSourceDO();
    solutionSourceDO.setSource(source);
    solutionSourceDO.setSolutionId(solutionDO.getId());
    solutionSourceMapper.insert(solutionSourceDO);
    //创建判题任务
    judgeExecute(problemDO, source, language.getLanguageName(), solutionDO);
  }

  /**
   * 创建判题任务,并处理判题结果
   * @param problemDO 题目
   * @param source 源码
   * @param language 语言
   * @param solution 对应判题记录
   */
  private void judgeExecute(ProblemDO problemDO, String source, String language,
      SolutionDO solution) {
    taskExecutor.execute(() -> {
      judgeClientPool.execute(judgeClient -> {
        JudgeResultResponse resultResponse = judgeClient.judge(solution.getId(), problemDO.getId(),
            source, language, problemDO.getTimeLimit(), problemDO.getMemoryLimit());
        //更新solution
        solution.setModifydate(new Date());
        solution.setTime(resultResponse.getUseTime());
        solution.setMemory(resultResponse.getUseMemory());
        solution.setVerdict(resultResponse.getExitCode());
        solutionMapper.updateByPrimaryKeySelective(solution);
        //根据返回错误码更新题目和用户信息
        dealWithByJudgeCode(resultResponse,problemDO.getId(),solution.getUserId());
        return false;
      });
    });
  }

  /**
   * 根据判题返回错误码更新用户和题目的最新信息
   * @param resultResponse 判题结果
   */
  private void dealWithByJudgeCode(JudgeResultResponse resultResponse,Long problemId,Long userId) {
    UserDO userDO = userMapper.selectByPrimaryKey(userId);
    ProblemDO problemDO = problemMapper.selectByPrimaryKey(problemId);
    //判断该题目是否被该用户AC过
    List<Long> solution = solutionMapper.queryIdByUserId(problemId, JudgeCode.AC.getStatus(),userId);
    //未被AC
    problemDO.setSubmit(problemDO.getSubmit()+1);
    userDO.setSubmit(userDO.getSubmit()+1);
    if (CollectionUtils.isEmpty(solution)){
      if (resultResponse.getExitCode().equals(JudgeCode.AC.getStatus())){
        userDO.setSolved(userDO.getSolved()+1);
      }
    }
    if (resultResponse.getExitCode().equals(JudgeCode.AC.getStatus())){
      problemDO.setSolved(problemDO.getSolved()+1);
    }
    problemMapper.updateByPrimaryKeySelective(problemDO);
    userMapper.updateByPrimaryKeySelective(userDO);
    // todo 重建一些显示上的缓存
  }
}
