package cn.edu.aust.controller;

import com.google.common.base.Preconditions;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

import javax.annotation.Resource;

import cn.edu.aust.pojo.entity.ProblemDO;
import cn.edu.aust.service.ProblemService;

/**
 * 判题控制器
 * @author Niu Li
 * @since 2017/3/14
 */
@Controller
@RequestMapping("/judge")
public class JudgerController {
  @Resource
  private ProblemService problemService;

  @PostMapping(value = "/problem/{id}",produces = MediaType.TEXT_HTML_VALUE)
  public String judger(@PathVariable("id") Long id,
      @RequestParam(value = "code") String sourceCode,
      @RequestParam(value = "way") Integer way){
    //登录限制
    ProblemDO problemDO = problemService.queryById(id);
    Preconditions.checkArgument(Objects.nonNull(problemDO),"所提交的题目不存在");
    Preconditions.checkArgument(StringUtils.isNotEmpty(sourceCode),"源代码不能为空");
    // todo 判题语言的支持与否校验


    return null;
  }
}
