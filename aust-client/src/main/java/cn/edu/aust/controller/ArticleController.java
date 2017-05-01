package cn.edu.aust.controller;


import com.github.pagehelper.PageInfo;
import com.google.common.collect.Sets;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cn.edu.aust.common.constant.PosCode;
import cn.edu.aust.common.entity.ResultVO;
import cn.edu.aust.common.util.CgiHelper;
import cn.edu.aust.dto.BaseArticleDTO;
import cn.edu.aust.dto.VoteStatusDTO;
import cn.edu.aust.exception.PageException;
import cn.edu.aust.pojo.entity.ArticleDO;
import cn.edu.aust.pojo.entity.UserDO;
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
    ResultVO<List<BaseArticleDTO>> resultVO = new ResultVO<>();
    List<BaseArticleDTO> asideDTOS = articleService.queryForAside(article_aside_limit);
    return resultVO.buildOKWithData(asideDTOS);
  }

  /**
   * 获取文章详情
   */
  @GetMapping(value = "/article/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO articleDetail(@PathVariable("id") Long id){
    ResultVO<ArticleDetailVO> resultVO = new ResultVO<>();
    ArticleDO articleDO = articleService.findDetailById(id);
    if (Objects.nonNull(articleDO.getIsShow()) && articleDO.getIsShow() == 0){
      return resultVO.buildWithMsgAndStatus(PosCode.NO_PRIVILEGE,"无权限查看");
    }
    //点击量控制
    articleService.viewHits(articleDO.getId(),articleDO.getViewCount());
    return resultVO.buildOKWithData(ArticleDetailVO.assembler(articleDO));
  }

  /**
   * 点赞文章
   * @param id 该文章id
   * @return 对应结果
   * @throws PageException 文章不存在或不显示抛出异常
   */
  @PostMapping(value = "/article/vote/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResultVO<?> articleVote(@PathVariable("id") Long id) throws PageException {
    ResultVO<VoteStatusDTO> resultVO = new ResultVO<>();
    //登录检查
    UserDO userDO = userService.getCurrent();
    if (userDO == null) {
      return resultVO.buildWithPosCode(PosCode.NO_LOGIN);
    }
    //查询
    BaseArticleDTO article = articleService.findBasicById(id);
    VoteStatusDTO voteStatusDTO = votelogService.voteArticleComment(article, userDO.getId());
    return resultVO.buildOKWithData(voteStatusDTO);
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
    //查询文章
    PageInfo<ArticleDO> articlePos = articleService.queryList(search,pageNum,pageSize);
    List<ArticleDO> articlePosList = articlePos.getList();
    //查询其中用户点赞过的文章
    UserDO loginUser = userService.getCurrent();
    Set<Long> userVoteIds = Sets.newHashSet();
    if (Objects.nonNull(loginUser)) {
      userVoteIds.addAll(votelogService.userLikeArticle(articlePosList.stream().map(ArticleDO::getId)
          .collect(Collectors.toList()), loginUser.getId()));
    }
    //包装返回
    ResultVO.paginationData<ArticleTableVO> tableVos = new ResultVO.paginationData<>(
        articlePos.getTotal(),
        articlePos.getPageSize(),
        ArticleTableVO.assembler(articlePosList,userVoteIds));
    return resultVO.buildOKWithData(tableVos);
  }

}
