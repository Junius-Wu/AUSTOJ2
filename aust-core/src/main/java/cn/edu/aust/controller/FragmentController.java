package cn.edu.aust.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import cn.edu.aust.Setting;
import cn.edu.aust.common.Principal;
import cn.edu.aust.common.entity.Article;
import cn.edu.aust.common.entity.Catelog;
import cn.edu.aust.common.entity.Tag;
import cn.edu.aust.common.entity.User;
import cn.edu.aust.service.ArticleService;
import cn.edu.aust.service.CatelogService;
import cn.edu.aust.service.TagService;
import cn.edu.aust.service.UserService;
import cn.edu.aust.util.SystemUtil;

/**
 * @author Niu Li
 * @date 2016/10/5
 */
@Controller
@RequestMapping(value = "/frg")
public class FragmentController {

    @Resource(name = "catelogServiceImpl")
    private CatelogService catelogService;
    @Resource(name = "tagServiceImpl")
    private TagService tagService;
    @Resource(name = "articleServiceImpl")
    private ArticleService articleService;
    @Resource(name = "userServiceImpl")
    private UserService userService;
    /**
     * 返回侧边栏,配合ajax返回html
     * @param isTags 为0,则不反回标签
     * @return
     */
    @RequestMapping(value = "/aside",method = RequestMethod.GET)
    public String asideFragment(@RequestParam(required = false,defaultValue = "0") int isTags,
                                HttpSession session, Model model){
        Setting setting = SystemUtil.getSetting();
        Principal principal = (Principal) session.getAttribute("userpri");
        if (principal != null){
            User user = userService.selectByPrimaryKey(principal.getId());
            model.addAttribute("user",user);

        }
        List<Catelog> catelogs = catelogService.selectList(0);
        if (isTags !=0){
            List<Tag> tags = tagService.selectList(setting.getAside_tags());
            model.addAttribute("tags",tags);
        }
        List<Article> articles = articleService.selectList(setting.getAside_articles());
        model.addAttribute("catelogs",catelogs);
        model.addAttribute("articles",articles);
        return "fragment/aside :: #asideFragment";
    }

}
