package cn.edu.aust.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.aust.common.util.WebUtils;
import cn.edu.aust.convert.ArticleConvert;
import cn.edu.aust.dto.ArticleAsideDTO;
import cn.edu.aust.dto.ArticleDTO;
import cn.edu.aust.mapper.ArticleMapper;
import cn.edu.aust.pojo.entity.ArticleDO;
import cn.edu.aust.pojo.entity.UserDO;
import cn.edu.aust.query.ArticlePO;
import cn.edu.aust.query.ArticleQuery;

/**
 * @author Niu Li
 * @since  2017/1/29
 */
@Service
public class ArticleService {
  @Resource
  private ArticleMapper articleMapper;
  @Resource
  private UserService userService;

  /**
   * 根据主键查询展示对象
   *
   * @param id 主键
   * @return 详情展示对象
   */
  public ArticleDTO findDetailById(Long id) {
    ArticlePO article = articleMapper.queryDetail(id);
    return ArticleConvert.articlePo2dto(article);
  }

  /**
   * 查询文章基本信息
   * @param id 文章id
   * @return 查询结果
   */
  public ArticleDTO findBasicById(Long id){
    ArticleDO articleDO = articleMapper.selectByPrimaryKey(id);
    return ArticleConvert.do2dto(articleDO);
  }

  /**
   * 增加点击次数
   * @param articleDTO 文章实体
   * @return 点击次数
   */
  @Transactional
  public int viewHits(ArticleDTO articleDTO) {
    ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = requestAttributes.getRequest();
    HttpServletResponse response = requestAttributes.getResponse();
    Long id = articleDTO.getId();
    String articles = WebUtils.getCookie(request, ArticleDTO.ARTICLEHIT_COOKIES);
    boolean canAdd = false;
    //不存在则添加
    if (articles == null) {
      WebUtils.addCookie(response, ArticleDTO.ARTICLEHIT_COOKIES, id + ",", 24 * 3600, "/", null, false);
      canAdd = true;
    } else {
      //cookies中不存在则添加
      if (articles.contains(id + ",")) {
        canAdd = false;
      } else {
        String value = articles + "," + id + ",";
        WebUtils.addCookie(response, ArticleDTO.ARTICLEHIT_COOKIES, value, 24 * 3600, "/", null, false);
        canAdd = true;
      }
    }
    if (canAdd) {
      ArticleDO articleDO = new ArticleDO();
      articleDO.setId(articleDTO.getId());
      int viewcount = articleDTO.getViewCount() + 1;
      articleDO.setViewcount(viewcount);
      articleMapper.updateByPrimaryKeySelective(articleDO);
      return viewcount;
    }
    return articleDTO.getViewCount();
  }

  /**
   * 查询侧边栏显示的文章
   * @param limit 数量
   * @return 映射集合
   */
  public List<ArticleAsideDTO> queryForAside(Integer limit) {
    PageHelper.offsetPage(0, limit, false);
    List<ArticleDO> articleDOS = articleMapper.queryForAside();
    return ArticleConvert.do2AsideDto(articleDOS);
  }

  /**
   * 查询文章展示类
   *
   * @param pageNum 页码
   * @param pageSize   每页数量
   * @return 结果集
   */
  public PageInfo<ArticlePO> queryList(String search, Integer pageNum, Integer pageSize) {
    //查询条件
    ArticleQuery articleQuery = new ArticleQuery();
    articleQuery.setSearch(search);
    UserDO userDO = userService.getCurrent();
    if (Objects.nonNull(userDO)) {
      articleQuery.setUserId(userDO.getId());
    }
    //分页查询并转换结果
    Page<ArticlePO> articlePCS = PageHelper.startPage(pageNum, pageSize).doSelectPage(
        () -> articleMapper.queryList(articleQuery)
    );
    PageInfo<ArticlePO> pageInfo = new PageInfo<>();
    pageInfo.setTotal(articlePCS.getTotal());
    pageInfo.setList(articlePCS.getResult());
    return pageInfo;
  }
}
