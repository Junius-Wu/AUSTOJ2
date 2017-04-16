package cn.edu.aust.controller;

import com.github.pagehelper.PageInfo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cn.edu.aust.common.constant.PosCode;
import cn.edu.aust.common.entity.ResultVO;
import cn.edu.aust.common.util.CgiHelper;
import cn.edu.aust.dto.ArticleAsideDTO;
import cn.edu.aust.dto.ArticleDTO;
import cn.edu.aust.exception.PageException;
import cn.edu.aust.pojo.entity.UserDO;
import cn.edu.aust.query.ArticlePO;
import cn.edu.aust.service.ArticleService;
import cn.edu.aust.service.UserService;
import cn.edu.aust.service.VotelogService;
import cn.edu.aust.vo.ArticleDetailVO;
import cn.edu.aust.vo.ArticleTableVO;

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
   * 获取文章详情
   */
  @GetMapping(value = "/article/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO articleDetail(@PathVariable("id") Long id){
    ResultVO<ArticleDetailVO> resultVO = new ResultVO<>();
    ArticleDTO articleDTO = articleService.findDetailById(id);
    if (Objects.nonNull(articleDTO.getIsShow()) && articleDTO.getIsShow() == 0){
      return resultVO.buildWithMsgAndStatus(PosCode.NO_PRIVILEGE,"无权限查看");
    }
    //点击量控制
    articleService.viewHits(articleDTO);
    return resultVO.buildOKWithData(ArticleDetailVO.assembler(articleDTO));
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
    ResultVO resultVO = new ResultVO();
    //登录检查
    UserDO userDO = userService.getCurrent();
    if (userDO == null) {
      return resultVO.buildWithPosCode(PosCode.NO_LOGIN);
    }
    //查询
    ArticleDTO article = articleService.findBasicById(id);

//    votelogService.voteArticleComment(result, article.get(), userDO.getId());
    return resultVO;
  }

  /**
   * 获取文章列表
   */
  @GetMapping(value = "/articles",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO articles(HttpServletRequest request){
    ResultVO<ResultVO.paginationData> resultVO = new ResultVO<>();
    //获取参数
    String search = CgiHelper.getString("search",null,request);
    Integer pageNum = CgiHelper.getPageNum(request);
    Integer pageSize = CgiHelper.getPageSize(request);
    //查询
    PageInfo<ArticlePO> articlePos = articleService.queryList(search,pageNum,pageSize);
    //包装返回
    ResultVO.paginationData<ArticleTableVO> tableVos = new ResultVO.paginationData<>(
        articlePos.getTotal(),
        articlePos.getPageSize(),
        ArticleTableVO.assembler(articlePos.getList()));
    return resultVO.buildOKWithData(tableVos);
  }

}
