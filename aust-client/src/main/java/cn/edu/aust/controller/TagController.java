package cn.edu.aust.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.Resource;

import cn.edu.aust.common.entity.ResultVO;
import cn.edu.aust.pojo.entity.TagsDO;
import cn.edu.aust.service.TagService;

/**
 * 标签控制
 * @author Niu Li
 * @since 2017/4/8
 */
@RestController
public class TagController {
  @Resource
  private TagService tagService;

  private static final Integer tags_limit = 30;

  /**
   * 获取排名前30的标签
   * @return 标签集合
   */
  @GetMapping(value = "/tags",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO tags(){
    ResultVO<List<TagsDO>> resultVO = new ResultVO<>();
    List<TagsDO> tagsDOS = tagService.queryList(tags_limit);
    return resultVO.buildOKWithData(tagsDOS);
  }
}
