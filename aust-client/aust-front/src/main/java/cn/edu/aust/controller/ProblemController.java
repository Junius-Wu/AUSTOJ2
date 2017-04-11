package cn.edu.aust.controller;

import com.github.pagehelper.PageInfo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cn.edu.aust.common.entity.ResultVO;
import cn.edu.aust.common.util.CgiHelper;
import cn.edu.aust.dto.ProblemListDTO;
import cn.edu.aust.service.ProblemService;
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
