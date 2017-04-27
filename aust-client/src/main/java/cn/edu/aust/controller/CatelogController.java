package cn.edu.aust.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.Resource;

import cn.edu.aust.common.entity.ResultVO;
import cn.edu.aust.dto.CatelogDTO;
import cn.edu.aust.service.CatelogService;

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
}
