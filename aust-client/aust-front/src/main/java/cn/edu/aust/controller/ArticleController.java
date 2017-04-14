package cn.edu.aust.controller;

import com.alibaba.fastjson.JSONObject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import cn.edu.aust.common.constant.PosCode;
import cn.edu.aust.common.entity.ResultVO;
import cn.edu.aust.dto.ArticleAsideDTO;
import cn.edu.aust.dto.ArticleDTO;
import cn.edu.aust.exception.PageException;
import cn.edu.aust.pojo.entity.UserDO;
import cn.edu.aust.service.ArticleService;
import cn.edu.aust.service.UserService;
import cn.edu.aust.service.VotelogService;

/**
 * @author Niu Li
 * @since  2017/1/29
 */
@RestController
public class ArticleController {
  @Resource
  private ArticleService articleService;
  @Resource
  private UserService userService;
  @Resource
  private VotelogService votelogService;

  private static final Integer article_aside_limit = 7;

  /**
   * 获取侧边展示文章
   */
  @GetMapping(value = "/articles/aside",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO articleAside(){
    ResultVO<List<ArticleAsideDTO>> resultVO = new ResultVO<>();
    List<ArticleAsideDTO> asideDTOS = articleService.queryForAside(article_aside_limit);
    return resultVO.buildOKWithData(asideDTOS);
  }

  /**
   * 点赞文章
   *
   * @param id 该文章id
   * @return 对应结果
   * @throws PageException 文章不存在或不显示抛出异常
   */
  @PostMapping(value = "/article/vote/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
    return new ResultVO<>(PosCode.OK, result);
  }

//  /**
//   * 获取文章列表
//   */
//  @GetMapping(value = "/articles",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//  public ResultVO articles(HttpServletRequest request){
//    return null;
//  }

}
