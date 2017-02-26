package cn.edu.aust.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageException;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import cn.edu.aust.common.constant.PosCode;
import cn.edu.aust.dto.ProblemListDTO;
import cn.edu.aust.entity.PageRequest;
import cn.edu.aust.pojo.entity.Catelog;
import cn.edu.aust.service.CatelogService;
import cn.edu.aust.service.ProblemService;

/**
 * 目录题目查询
 * @author Niu Li
 * @since 2017/2/26
 */
@Controller
public class CatelogController {

  @Resource
  private CatelogService catelogService;
  @Resource
  private ProblemService problemService;

  /**
   * 前往目录页面
   * @param id 该目录的id
   * @return 视图
   */
  @GetMapping(value = "/catelog/{id}",produces = MediaType.TEXT_HTML_VALUE)
  public String toCatelog(@PathVariable("id") Integer id, Model model){
    Catelog catelog = catelogService.queryById(id);
    if (catelog == null){
      throw new PageException("所查看的目录不存在");
    }
    model.addAttribute("cateName",catelog.getName());
    model.addAttribute("cateId",catelog.getId());
    return "searchpro";
  }


  /**
   * 查看指定目录下的题目
   * @param id 目录id
   * @return 视图
   */
  @ResponseBody
  @GetMapping(value = "/problem/catelog/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JSONObject queryCategoryProblem(@PathVariable("id") Integer id, PageRequest pageRequest){
    JSONObject result = new JSONObject();

    Catelog catelog = catelogService.queryById(id);
    if (catelog == null){
      result.put("status", PosCode.NO_PRIVILEGE.getStatus());
      result.put("msg", PosCode.NO_PRIVILEGE.getMsg());
      return result;
    }
    //查找题目

    PageInfo<ProblemListDTO> pageInfo = problemService.queryListStage(pageRequest.getSearch(),
                                                                      id,
                                                                      pageRequest.getOrder(),
                                                                      pageRequest.getOffset(),
                                                                      pageRequest.getLimit(),
                                                                      true);
    result.put("rows",pageInfo.getList());
    result.put("total",pageInfo.getTotal());
    return result;
  }

}
