package cn.edu.aust.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import cn.edu.aust.common.mybatis.Filter;
import cn.edu.aust.common.util.PageRequest;
import cn.edu.aust.exception.PageException;
import cn.edu.aust.service.CatelogService;
import cn.edu.aust.service.ProblemService;

import static cn.edu.aust.common.ResultVo.NO_PRIVILEGE;

/**
 * 目录控制器
 * @author Niu Li
 * @date 2016/11/24
 */
@Controller
@RequestMapping("/catelog")
public class CatelogController {

    @Resource(name = "catelogServiceImpl")
    private CatelogService catelogService;
    @Resource(name = "problemServiceImpl")
    private ProblemService problemService;
    /**
     * 找到指定目录下的题目
     * @param id 目录id
     * @return 对应页面
     */
    @RequestMapping(value = "/pro/{id}",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String catelogProblem(@PathVariable("id") Integer id, Model model) throws PageException {
        Optional<Catelog> catelog = Optional.ofNullable(catelogService.selectByPrimaryKey(id));
        catelog.filter(catelog1 -> catelog1.getType()==0)
               .orElseThrow(()->new PageException(NO_PRIVILEGE));
        //上面的orElseThrow已经检查null了,所以忽略IDEA的警告
        model.addAttribute("catelog",catelog.get().getName());
        model.addAttribute("catelogId",id);
        return "searchpro";
    }

    /**
     * 分页查找对应目录下的题目
     * @param id  目录id
     * @param pageRequest 分页参数
     * @return 分页数据
     */
    @ResponseBody
    @RequestMapping(value = "/table/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject catelogProblemTable(@PathVariable("id") Integer id, PageRequest pageRequest) {
        JSONObject result = new JSONObject();
        pageRequest.getFilters().add(Filter.eq("contest_id",0));//非竞赛题
        pageRequest.getFilters().add(Filter.eq("catelog",(byte)id.intValue()));//指定目录

        List<Problem> problems = PageHelper.startPage((pageRequest.getOffset()/ pageRequest.getLimit())+1, pageRequest.getLimit())
                                           .setOrderBy(pageRequest.getOrdername()+" "+ pageRequest.getOrder())
                                           .doSelectPage(
                                                   ()->problemService.selectWithPageRequest(pageRequest)
                                           );
        result.put("rows",problems);
        result.put("total",((Page)problems).getTotal());
        return result;
    }


}
