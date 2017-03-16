package cn.edu.aust.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.edu.aust.common.constant.PosCode;
import cn.edu.aust.common.entity.ResultVO;
import cn.edu.aust.common.util.CgiHelper;
import cn.edu.aust.dto.ContestDTO;
import cn.edu.aust.dto.ProblemDTO;
import cn.edu.aust.dto.ProblemListDTO;
import cn.edu.aust.exception.PageException;
import cn.edu.aust.pojo.entity.UserDO;
import cn.edu.aust.service.ContestService;
import cn.edu.aust.service.ProblemService;
import cn.edu.aust.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * 竞赛服务
 *
 * @author Niu Li
 * @since 2017/2/26
 */
@Controller
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
   * 前往首页
   *
   * @return 首页模板
   */
  @GetMapping(produces = "text/html;charset=UTF-8")
  public String toContest(Model model) {
    Map<String, List<ContestDTO>> resultMap = contestService.queryAndKinds();
    model.addAllAttributes(resultMap);
    return "contest";
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
  public ResultVO<PosCode> canViewContest(@PathVariable(value = "id") Long id,
                                        HttpServletRequest request,
                                        HttpSession session) {
    UserDO userDO = userService.getCurrent();
    if (userDO == null) {
      return new ResultVO<PosCode>(PosCode.NO_LOGIN);
    }
    try {
      //判断是否验证过
      String curContest = (String) session.getAttribute("contest");
      if (StringUtils.isEmpty(curContest)) {
        curContest = "";
      } else {
        String[] ids = StringUtils.split(curContest, ",");
        if (Arrays.binarySearch(ids, String.valueOf(id)) >= 0) {
          return new ResultVO<PosCode>(PosCode.OK);
        }
      }
      //检查是否可以访问
      String passwd = CgiHelper.getString("passwd", null, request);
      if (contestService.canView(id, passwd)) {
        session.setAttribute("contest", curContest + "," + id);
        return new ResultVO<PosCode>(PosCode.OK);
      }
    }catch (Exception e){
      log.error("访问比赛出错",e);
      return new ResultVO<PosCode>(PosCode.NO_PRIVILEGE.getStatus(),e.getMessage());
    }
    return new ResultVO<PosCode>(PosCode.NO_PRIVILEGE);
  }

  /**
   * 查看一个竞赛详情
   *
   * @param id      该竞赛id
   * @param session session
   * @return 该视图
   */
  @GetMapping(value = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
  public String contestDetail(@PathVariable("id") Long id,
                              HttpSession session,
                              Model model) throws PageException {
    UserDO userDO = userService.getCurrent();
    if (userDO == null) {
      throw new PageException("用户未登录");
    }
    //判断是否验证过
    String curContest = (String) session.getAttribute("contest");
    if (StringUtils.isEmpty(curContest)) {
      throw new PageException("用户没有权限查看");
    } else {
      String[] ids = StringUtils.split(curContest, ",");
      if (Arrays.binarySearch(ids, String.valueOf(id)) < 0) {
        throw new PageException("用户没有权限查看");
      }
    }
    //查找竞赛
    ContestDTO contest = contestService.findDetail(id);
    //查找相关题目
    List<ProblemListDTO> problems = problemService.queryContest(id);
    //返回视图
    model.addAttribute("contest", contest);
    model.addAttribute("problems", problems);
    return "contestdetail";
  }

  /**
   * 查看比赛题目详情
   * @param id 该题目id
   * @return 视图
   * @throws PageException 非法访问
   */
  @GetMapping(value = "/problem/{id}", produces = MediaType.TEXT_HTML_VALUE)
  public String contestProblem(@PathVariable("id") Long id,
                               HttpSession session,
                               Model model) throws PageException {
    UserDO userDO = userService.getCurrent();
    if (userDO == null) {
      throw new PageException("用户未登录");
    }
    //查询题目
    ProblemDTO problemDTO = problemService.queryContestProblem(id, session);
    model.addAttribute("problem",problemDTO);
    return "problem";
  }
}
