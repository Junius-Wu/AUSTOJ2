package cn.edu.aust.controller;

import com.github.pagehelper.PageInfo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cn.edu.aust.common.constant.PosCode;
import cn.edu.aust.common.entity.ResultVO;
import cn.edu.aust.common.util.CgiHelper;
import cn.edu.aust.dto.ProblemDTO;
import cn.edu.aust.dto.ProblemListDTO;
import cn.edu.aust.pojo.entity.UserDO;
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

  /**
   * 得到一个题目的详情
   * @param id 题目id
   * @return 详情
   */
  @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO problem(@PathVariable("id") Long id){
    ResultVO<ProblemDTO> resultVO = new ResultVO<>();
    if (id < 1000L || id > 10000L){
      return resultVO.buildWithMsgAndStatus(PosCode.NO_PRIVILEGE,"不存在的题目或者无权访问");
    }
    ProblemDTO problemDTO = problemService.findDetail(id);
    if (Objects.isNull(problemDTO)) {
      return resultVO.buildWithMsgAndStatus(PosCode.NO_PRIVILEGE,"不存在的题目或者无权访问");
    }
    //如果是竞赛题
    if (!problemDTO.getContestId().equals(-1L)){
      UserDO userDO = userService.getCurrent();
      if (Objects.isNull(userDO)){
        return resultVO.buildWithMsgAndStatus(PosCode.NO_LOGIN,"无权查看竞赛题");
      }
      if (!contestService.isVisited(problemDTO.getContestId(),userDO.getId())){
        return resultVO.buildWithMsgAndStatus(PosCode.NO_PRIVILEGE,"无权查看竞赛题");
      }
    }
    //构造返回
    return resultVO.buildOKWithData(problemDTO);
  }


  /**
   * 查询对应阶段的题目
   *
   * @param stage 指定阶段
   * @return 结果集
   */
  @ResponseBody
  @GetMapping(value = "/stage/{stage}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO listStage(@PathVariable(value = "stage") Integer stage,
      HttpServletRequest request) {
    ResultVO<ProblemTableVO> resultVO = new ResultVO<>();
    String search = CgiHelper.getString("search", null, request);
    String order = CgiHelper.getString("order", "asc", request);
    Integer pageSize = CgiHelper.getPageSize(request);
    Integer pageNum = CgiHelper.getPageNum(request);

    PageInfo<ProblemListDTO> pageInfo = problemService.queryListStage(search,
        stage, order, pageNum, pageSize, false);
    ProblemTableVO tableVO = ProblemTableVO.assemble(pageInfo.getList(), pageInfo.getTotal(), pageNum);
    return resultVO.buildOKWithData(tableVO);
  }

}
