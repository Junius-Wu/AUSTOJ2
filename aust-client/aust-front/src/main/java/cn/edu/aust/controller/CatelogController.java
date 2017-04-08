package cn.edu.aust.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import cn.edu.aust.common.constant.PosCode;
import cn.edu.aust.common.entity.ResultVO;
import cn.edu.aust.dto.CatelogDTO;
import cn.edu.aust.dto.ProblemListDTO;
import cn.edu.aust.entity.PageRequest;
import cn.edu.aust.service.CatelogService;
import cn.edu.aust.service.ProblemService;

/**
 * 目录题目查询
 *
 * @author Niu Li
 * @since 2017/2/26
 */
@RestController
public class CatelogController {

  @Resource
  private CatelogService catelogService;
  @Resource
  private ProblemService problemService;

  /**
   * 拿到全部的目录
   * @return 全部目录
   */
  @GetMapping(value = "/catelogs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
  public ResultVO catelogs(){
    ResultVO<List<CatelogDTO>> resultVO = new ResultVO<>();
    List<CatelogDTO> catelogDTOS = catelogService.queryAll();
    return resultVO.buildOKWithData(catelogDTOS);
  }

  /**
   * 查看指定目录下的题目
   *
   * @param id 目录id
   * @return 视图
   */
  @GetMapping(value = "/problem/catelog/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public JSONObject queryCategoryProblem(@PathVariable("id") Integer id, PageRequest pageRequest) {
    JSONObject result = new JSONObject();

    CatelogDTO catelogDTO = catelogService.findById(id);
    if (Objects.isNull(catelogDTO)) {
      result.put("status", PosCode.NO_PRIVILEGE.getStatus());
      result.put("msg", PosCode.NO_PRIVILEGE.getMsg());
      return result;
    }

    //查找题目
    PageInfo<ProblemListDTO> pageInfo = problemService.queryListStage(pageRequest.getSearch(),
        id, pageRequest.getOrder(), pageRequest.getOffset(), pageRequest.getLimit(), true);
    result.put("rows", pageInfo.getList());
    result.put("total", pageInfo.getTotal());
    return result;
  }

}
