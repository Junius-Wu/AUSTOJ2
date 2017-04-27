package cn.edu.aust.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cn.edu.aust.common.constant.PosCode;
import cn.edu.aust.common.entity.ResultVO;
import cn.edu.aust.common.util.CgiHelper;
import cn.edu.aust.dto.ContestDTO;
import cn.edu.aust.dto.ProblemBasicDTO;
import cn.edu.aust.exception.PageException;
import cn.edu.aust.pojo.entity.UserDO;
import cn.edu.aust.service.ContestService;
import cn.edu.aust.service.ProblemService;
import cn.edu.aust.service.UserService;
import cn.edu.aust.vo.ContestDetailVO;
import cn.edu.aust.vo.ContestTableVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 竞赛服务
 *
 * @author Niu Li
 * @since 2017/2/26
 */
@RestController
@RequestMapping("/contest")
@Slf4j
public class ContestController {

  @Resource
  private ContestService contestService;
  @Resource
  private UserService userService;
  @Resource
  private ProblemService problemService;

  /**
   * 获取竞赛首页数据
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO contests(){
    ResultVO<ContestTableVO> resultVO = new ResultVO<>();
    Map<String, List<ContestDTO>> contests = contestService.queryAndKinds();
    return resultVO.buildOKWithData(ContestTableVO.assemble(contests));
  }

  /**
   * 判断一个比赛是否可以访问,若可以访问则给当前用户添加上信息
   *
   * @param id      该比赛id
   * @param request 请求信息
   * @return 结果
   */
  @PostMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public ResultVO canViewContest(@PathVariable(value = "id") Long id,
                                        HttpServletRequest request) {
    ResultVO<PosCode> resultVO = new ResultVO<>();
    UserDO userDO = userService.getCurrent();
    if (Objects.isNull(userDO)) {
      return resultVO.buildWithPosCode(PosCode.NO_LOGIN);
    }
    try {
      //判断是否访问过
      if (contestService.isVisited(id,userDO.getId())){
        return resultVO.buildOK();
      }
      //检查是否可以访问
      String passwd = CgiHelper.getString("passwd", null, request);
      if (contestService.canView(id,userDO.getId(),passwd)) {
        return resultVO.buildOK();
      }
    }catch (Exception e){
      log.error("can't view contest: {}",id,e);
      return resultVO.buildWithMsgAndStatus(PosCode.NO_PRIVILEGE,e.getMessage());
    }
    return resultVO.buildWithPosCode(PosCode.NO_PRIVILEGE);
  }

  /**
   * 查看一个竞赛详情
   *
   * @param id      该竞赛id
   * @return 该视图
   */
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO contestDetail(@PathVariable("id") Long id) throws PageException {
    ResultVO<ContestDetailVO> resultVO = new ResultVO<>();
    UserDO userDO = userService.getCurrent();
    if (Objects.isNull(userDO)) {
      return resultVO.buildWithMsgAndStatus(PosCode.NO_LOGIN, "用户未登录");
    }
    //判断是否验证过
    if (!contestService.isVisited(id, userDO.getId())) {
      return resultVO.buildWithMsgAndStatus(PosCode.NO_PRIVILEGE, "用户无权限访问");
    }
    //查找竞赛
    ContestDTO contest = contestService.findDetail(id);
    if (Objects.isNull(contest)) {
      return resultVO.buildWithMsgAndStatus(PosCode.NO_PRIVILEGE, "用户无权限访问");
    }
    //查找相关题目
    List<ProblemBasicDTO> problems = problemService.queryContest(id);

    return resultVO.buildOKWithData(ContestDetailVO.assemble(problems,contest));
  }
}
