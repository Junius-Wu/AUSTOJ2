package cn.edu.aust.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

import javax.annotation.Resource;

import cn.edu.aust.common.constant.PosCode;
import cn.edu.aust.common.entity.ResultVO;
import cn.edu.aust.dto.ArticleDTO;
import cn.edu.aust.dto.ArticleListDTO;
import cn.edu.aust.entity.PageRequest;
import cn.edu.aust.exception.PageException;
import cn.edu.aust.pojo.entity.UserDO;
import cn.edu.aust.service.ArticleService;
import cn.edu.aust.service.UserService;
import cn.edu.aust.service.VotelogService;

/**
 * @author Niu Li
 * @date 2017/1/29
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
  @Resource
  private ArticleService articleService;
  @Resource
  private UserService userService;
  @Resource
  private VotelogService votelogService;

  /**
   * 查看指定文章
   *
   * @param id 该文章id
   * @return 该文章页面
   */
  @GetMapping(value = "/{id}", produces = "text/html;charset=UTF-8")
  public String showArticle(@PathVariable("id") Long id, Model model) throws PageException {
    Optional<ArticleDTO> article = Optional.of(articleService.findDetailById(id));
    article.orElseThrow(() -> new PageException(PosCode.NO_PRIVILEGE.getMsg()));
    articleService.viewHits(article.get());
    model.addAttribute("article", article.get());
    return "article";
  }

  /**
   * 前往文章页面
   *
   * @param pageRequest 分页参数包装
   * @return 该页面
   */
  @GetMapping(produces = "text/html;charset=UTF-8")
  public String queryForList(PageRequest pageRequest, Model model) {
    pageRequest.setLimit(6);
    PageInfo<ArticleListDTO> pageInfo = articleService.queryList(pageRequest.getSearch(),
        pageRequest.getPageNum(),
        pageRequest.getLimit());
    model.addAttribute("pageinfo", pageInfo);
    model.addAttribute("search", pageRequest.getSearch());
    return "articles";
  }

  /**
   * 点赞文章
   *
   * @param id 该文章id
   * @return 对应结果
   * @throws PageException 文章不存在或不显示抛出异常
   */
  @ResponseBody
  @PostMapping(value = "/vote/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO<?> articleVote(@PathVariable("id") Long id) throws PageException {
    JSONObject result = new JSONObject();
    // todo 用户验证方式去除
    UserDO userDO = userService.getCurrent();
    if (userDO == null) {
      return new ResultVO<PosCode>(PosCode.NO_LOGIN);
    }
    Optional<ArticleDTO> article = Optional.of(articleService.findBasicById(id));
    article.filter(a -> a.getIsShow() != 0)
        .orElseThrow(() -> new PageException(PosCode.NO_PRIVILEGE.getMsg()));

    votelogService.voteArticleComment(result, article.get(), userDO.getId());
    return new ResultVO<JSONObject>(PosCode.OK, result);
  }


}
