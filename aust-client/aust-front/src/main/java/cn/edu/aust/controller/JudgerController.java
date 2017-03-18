package cn.edu.aust.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

import javax.annotation.Resource;

import cn.edu.aust.common.constant.PosCode;
import cn.edu.aust.common.entity.ResultVO;
import cn.edu.aust.common.util.LanguageUtil;
import cn.edu.aust.dto.SolutionDTO;
import cn.edu.aust.entity.PageRequest;
import cn.edu.aust.pojo.entity.ProblemDO;
import cn.edu.aust.pojo.entity.UserDO;
import cn.edu.aust.service.ProblemService;
import cn.edu.aust.service.SolutionService;
import cn.edu.aust.service.UserService;

/**
 * 判题控制器
 *
 * @author Niu Li
 * @since 2017/3/14
 */
@Controller
public class JudgerController {
  @Resource
  private ProblemService problemService;
  @Resource
  private UserService userService;
  @Resource
  private SolutionService solutionService;

  /**
   * 体检判题
   * @param id 题目id
   * @param sourceCode 源码
   * @param language 语言
   * @return 提交结果
   */
  @PostMapping(value = "/judge/problem/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public ResultVO judger(@PathVariable("id") Long id,
      @RequestParam(value = "code") String sourceCode,
      @RequestParam(value = "lang") String language) {
    //登录限制和参数检查
    UserDO loginUser = userService.getCurrent();
    ResultVO resultVO = new ResultVO();
    if (Objects.isNull(loginUser)){
      return resultVO.buildWithMsgAndStatus(PosCode.NO_LOGIN,"用户未登录");
    }
    ProblemDO problemDO = problemService.queryById(id);
    if(Objects.isNull(problemDO)){
      return resultVO.buildWithMsgAndStatus(PosCode.PARAM_ERROR,"所提交的题目不存在");
    }
    if (StringUtils.isEmpty(sourceCode)){
      return resultVO.buildWithMsgAndStatus(PosCode.PARAM_ERROR,"源代码不能为空");
    }
    LanguageUtil.Language lang = LanguageUtil.getLanguage(language);
    if (Objects.isNull(lang)){
      return resultVO.buildWithMsgAndStatus(PosCode.PARAM_ERROR,"所选语言不存在");
    }
    solutionService.startJudger(loginUser.getId(),problemDO,sourceCode,lang);
    return resultVO.buildOK();
  }

  /**
   * 前往判题列表
   * @param refreshCount 刷新表格次数
   * @return 该页面
   */
  @GetMapping(value = "/judge", produces = MediaType.TEXT_HTML_VALUE)
  public String judgePage(@RequestParam(value = "count",defaultValue = "0") Integer refreshCount, Model model){
    UserDO loginUser = userService.getCurrent();
    Preconditions.checkNotNull(loginUser,"用户未登录");
    model.addAttribute("refreshCount",Math.min(5,refreshCount));
    return "submit";
  }

  /**
   * 查询用户提交列表
   * @param pageRequest 分页请求
   * @return 查询结果
   */
  @GetMapping(value = "/judge/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public JSONObject judgeList(PageRequest pageRequest){
    JSONObject result = new JSONObject();
    UserDO loginUser = userService.getCurrent();
    if (Objects.isNull(loginUser)){
      result.put("status",PosCode.NO_LOGIN.getStatus());
      result.put("msg","用户未登录");
      return result;
    }
    //查询列表
    PageInfo<SolutionDTO> data = solutionService.userSolutionList(pageRequest.getSearch(), loginUser.getId(),
        pageRequest.getOffset(), pageRequest.getLimit());
    result.put("rows",data.getList());
    result.put("total",data.getTotal());
    return result;
  }



}
