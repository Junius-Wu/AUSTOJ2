package cn.edu.aust.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import cn.edu.aust.common.Filter;
import cn.edu.aust.common.PageAble;
import cn.edu.aust.common.entity.Problem;
import cn.edu.aust.common.entity.ProblemBLOBs;
import cn.edu.aust.exception.PageException;
import cn.edu.aust.service.ProblemService;

import static cn.edu.aust.common.ResultVo.PROBLEM_NOT_EXIST;

/**
 * @author Niu Li
 * @date 2016/10/5
 */
@Controller
@RequestMapping(value = "/problem")
public class ProblemController {

    @Resource(name = "problemServiceImpl")
    private ProblemService problemService;

    /**
     * 查找相应阶段的题目
     * @param stage
     * @return
     */
    @RequestMapping(value = "/findByStage/{stage}",method = RequestMethod.GET)
    public @ResponseBody
    JSONObject findByStage(@PathVariable(value = "stage") Integer stage, PageAble pageAble){
        JSONObject result = new JSONObject();
        pageAble.getFilters().add(Filter.eq("stage",stage));
        pageAble.getFilters().add(Filter.eq("contest_id",0));

        PageHelper.startPage((pageAble.getOffset()/pageAble.getLimit())+1,pageAble.getLimit());
        PageHelper.orderBy(pageAble.getOrdername()+" "+pageAble.getOrder());
        List<Problem> problems = problemService.selectWithCriteria(pageAble);

        result.put("rows",problems);
        result.put("total",((Page)problems).getTotal());
        return result;
    }

    /**
     * 查找一个具体的题目,并前往题目页面
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String findProblem(@PathVariable(value = "id") Integer id, Model model) throws PageException {
        Optional<ProblemBLOBs> problem = Optional.ofNullable(problemService.selectByPrimaryKey(id));
        model.addAttribute("problem", problem.orElseThrow(() -> new PageException(PROBLEM_NOT_EXIST)));
        return "problem";
    }
}
