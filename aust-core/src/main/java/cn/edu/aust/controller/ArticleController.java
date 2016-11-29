package cn.edu.aust.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

import javax.annotation.Resource;

import cn.edu.aust.common.ResultVo;
import cn.edu.aust.common.constant.Contants;
import cn.edu.aust.common.entity.Article;
import cn.edu.aust.common.entity.User;
import cn.edu.aust.common.entity.pojo.ArticleUser;
import cn.edu.aust.common.util.ReturnUtil;
import cn.edu.aust.common.util.StringUtils;
import cn.edu.aust.exception.PageException;
import cn.edu.aust.service.ArticleService;
import cn.edu.aust.service.TagService;
import cn.edu.aust.service.UserService;
import cn.edu.aust.service.VoteLogService;

/**
 * @author Niu Li
 * @date 2016/11/28
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
    @Resource(name = "articleServiceImpl")
    private ArticleService articleService;
    @Resource(name = "tagServiceImpl")
    private TagService tagService;
    @Resource(name = "userServiceImpl")
    private UserService userService;
    @Resource(name = "voteLogServiceImpl")
    private VoteLogService voteLogService;
    /**
     * 文章列表主页
     * @param search 搜索内容
     * @return 该视图
     */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String articles(String search,Model model,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "5") Integer pageSize) {

        User user = userService.getCurrent();

        PageInfo<ArticleUser> pageInfo = PageHelper.startPage(pageNum, pageSize)
                                               .doSelectPageInfo(() -> {
                                                   String select = null;
                                                   if (!StringUtils.isEmpty(search)) {
                                                       //记录搜索热词
                                                       if (search.length() < Contants.ARTICLE_SEARCH__NUM){
                                                           tagService.insertAndFlush(search);
                                                       }
                                                       select = "%" + search + "%";
                                                   }
                                                   articleService.selectAll(user,select);
                                               });
        model.addAttribute("pageinfo",pageInfo);
        model.addAttribute("search",search);
        return "articles";
    }

    @ResponseBody
    @RequestMapping(value = "/vote/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject articleVote(@PathVariable("id") Integer id) throws PageException {
        JSONObject result = new JSONObject();
        User user = userService.getCurrent();
        if (user == null){
            return ReturnUtil.packingRes(result,ResultVo.NOT_LOGIN);
        }

        Optional<Article> article = Optional.of(articleService.selectByPrimaryKey(id));
        article.filter(article1 -> article1.getIsshow() != 0)
               .orElseThrow(()->new PageException(ResultVo.NO_PRIVILEGE));

        voteLogService.voteArticleComment(result,article.get(),user.getId());
        return ReturnUtil.packingRes(result,ResultVo.OK);
    }
}
