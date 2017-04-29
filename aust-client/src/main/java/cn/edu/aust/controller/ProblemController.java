package cn.edu.aust.controller;

import com.github.pagehelper.PageInfo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cn.edu.aust.common.constant.PosCode;
import cn.edu.aust.common.constant.ProblemType;
import cn.edu.aust.common.entity.ResultVO;
import cn.edu.aust.common.util.CgiHelper;
import cn.edu.aust.dto.BaseProblemDTO;
import cn.edu.aust.dto.CatelogDTO;
import cn.edu.aust.pojo.entity.ProblemDO;
import cn.edu.aust.pojo.entity.UserDO;
import cn.edu.aust.service.CatelogService;
import cn.edu.aust.service.ContestService;
import cn.edu.aust.service.ProblemService;
import cn.edu.aust.service.UserService;
import cn.edu.aust.vo.ProblemTableVO;

/**
 * 题目控制器
 *
 * @author Niu Li
 * @date 2017/1/29
 */
@RestController
@RequestMapping("/problem")
public class ProblemController {

  @Resource
  private ProblemService problemService;
  @Resource
  private ContestService contestService;
  @Resource
  private UserService userService;
  @Resource
  private CatelogService catelogService;

  /**
   * 得到一个题目的详情
   * @param id 题目id
   * @return 详情
   */
  @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO problem(@PathVariable("id") Long id,
      @RequestParam(value = "contest_id",required = false) Long contestId){
    ResultVO<ProblemDO> resultVO = new ResultVO<>();
    if (id < 1000L || id > 10000L){
      return resultVO.buildWithMsgAndStatus(PosCode.NO_PRIVILEGE,"不存在的题目或者无权访问");
    }
    ProblemDO problemDO = problemService.findDetail(id);
    if (Objects.isNull(problemDO)) {
      return resultVO.buildWithMsgAndStatus(PosCode.NO_PRIVILEGE,"不存在的题目或者无权访问");
    }
    //如果非是常规题
    if (problemDO.getType() == ProblemType.NORMAL.value){
      return resultVO.buildOKWithData(problemDO);
    }
    //竞赛类型题目
    if (problemDO.getType() == ProblemType.CONTEST.value){
      UserDO loginUser = userService.getCurrent();
      if (Objects.isNull(loginUser)) {
        return resultVO.buildWithMsgAndStatus(PosCode.NO_LOGIN, "用户未登录");
      }
      if (!contestService.isVisited(contestId, loginUser.getId())) {
        return resultVO.buildWithMsgAndStatus(PosCode.NO_PRIVILEGE,"没有权限查看竞赛题");
      }
    }
    //构造返回
    return resultVO.buildOKWithData(problemDO);
  }


  /**
   * 查询对应阶段的题目
   *
   * @param stage 指定阶段
   * @return 结果集
   */
  @GetMapping(value = "/stage/{stage}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO listStage(@PathVariable(value = "stage") Integer stage,
      HttpServletRequest request) {
    ResultVO<ProblemTableVO> resultVO = new ResultVO<>();
    String search = CgiHelper.getString("search", null, request);
    String order = CgiHelper.getString("order", "asc", request);
    Integer pageSize = CgiHelper.getPageSize(request);
    Integer pageNum = CgiHelper.getPageNum(request);

    PageInfo<BaseProblemDTO> pageInfo = problemService.queryListStage(search,
        stage, order, pageNum, pageSize, false);
    ProblemTableVO tableVO = ProblemTableVO.assemble(pageInfo.getList(), pageInfo.getTotal(), pageNum);
    return resultVO.buildOKWithData(tableVO);
  }
  /**
   * 查询对应目录的题目
   *
   * @param catelogId 指定目录
   * @return 结果集
   */
  @GetMapping(value = "/catelog/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO listCatelog(@PathVariable(value = "id") Integer catelogId,
      HttpServletRequest request) {
    ResultVO<ProblemTableVO> resultVO = new ResultVO<>();
    String search = CgiHelper.getString("search", null, request);
    String order = CgiHelper.getString("order", "asc", request);
    Integer pageSize = CgiHelper.getPageSize(request);
    Integer pageNum = CgiHelper.getPageNum(request);

    CatelogDTO catelogDTO = catelogService.findById(catelogId);
    if (Objects.isNull(catelogDTO)){
      return resultVO.buildWithMsgAndStatus(PosCode.NO_PRIVILEGE,"用户无权限访问");
    }
    PageInfo<BaseProblemDTO> pageInfo = problemService.queryListStage(search,
        catelogId, order, pageNum, pageSize, true);
    ProblemTableVO tableVO = ProblemTableVO.assemble(pageInfo.getList(), pageInfo.getTotal(), pageNum);
    tableVO.setCatelogName(catelogDTO.getName());
    return resultVO.buildOKWithData(tableVO);
  }

}
