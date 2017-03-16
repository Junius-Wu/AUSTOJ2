package cn.edu.aust.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.aust.dto.ProblemDTO;
import cn.edu.aust.dto.ProblemListDTO;
import cn.edu.aust.entity.PageRequest;
import cn.edu.aust.service.ProblemService;

/**
 * 题目控制器
 *
 * @author Niu Li
 * @date 2017/1/29
 */
@Controller
@RequestMapping("/problem")
public class ProblemController {

  @Autowired
  private ProblemService problemService;

  /**
   * 查询一个题目的详情
   *
   * @param id 该题目id
   * @return 视图
   */
  @GetMapping(value = "/{id}", produces = "text/html;charset=UTF-8")
  public String problemDetail(@PathVariable("id") Long id, Model model) {
    ProblemDTO problemDTO = problemService.findDetail(id);
    model.addAttribute("problem", problemDTO);
    return "problem";
  }

  /**
   * 查询对应阶段的题目
   *
   * @param stage       指定阶段
   * @param pageRequest 请求参数
   * @return 结果集
   */
  @ResponseBody
  @GetMapping(value = "/stage/{stage}", produces = "application/json; charset=UTF-8")
  public JSONObject listStage(@PathVariable(value = "stage") Integer stage,
                              PageRequest pageRequest) {
    JSONObject result = new JSONObject();

    PageInfo<ProblemListDTO> pageInfo = problemService.queryListStage(pageRequest.getSearch(),
                                                                      stage,
                                                                      pageRequest.getOrder(),
                                                                      pageRequest.getOffset(),
                                                                      pageRequest.getLimit(),
                                                                      false);
    result.put("rows", pageInfo.getList());
    result.put("total", pageInfo.getTotal());
    return result;
  }

}
