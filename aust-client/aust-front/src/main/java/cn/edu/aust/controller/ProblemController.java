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
import javax.servlet.http.HttpSession;

import cn.edu.aust.common.entity.pojo.ProblemUser;
import cn.edu.aust.common.mybatis.Filter;
import cn.edu.aust.common.util.PageRequest;
import cn.edu.aust.exception.PageException;
import cn.edu.aust.service.ProblemService;
import cn.edu.aust.service.UserService;

import static cn.edu.aust.common.ResultVo.PROBLEM_NOT_EXIST;

/**
 * 题目的控制器
 * @author Niu Li
 * @date 2016/10/5
 */
@Controller
@RequestMapping(value = "/problem")
public class ProblemController {

    @Resource(name = "problemServiceImpl")
    private ProblemService problemService;
    @Resource(name = "userServiceImpl")
    private UserService userService;

    /**
     * 查找相应阶段的题目
     * @param stage 对应的阶段 1 start 2 practice 3 master
     * @return 分页所需要的信息 total: rows:[]
     */
    @RequestMapping(value = "/findByStage/{stage}",method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
    public @ResponseBody JSONObject findByStage(@PathVariable(value = "stage") Integer stage, PageRequest pageRequest){
        JSONObject result = new JSONObject();
        pageRequest.getFilters().add(Filter.eq("stage",stage));
        pageRequest.getFilters().add(Filter.eq("contest_id",0));//非竞赛题

        List<Problem> problems = PageHelper.startPage((pageRequest.getOffset()/ pageRequest.getLimit())+1, pageRequest.getLimit())
                                           .setOrderBy(pageRequest.getOrdername()+" "+ pageRequest.getOrder())
                                           .doSelectPage(
                                                   ()->problemService.selectWithPageRequest(pageRequest)
                                           );

        result.put("rows",problems);
        result.put("total",((Page)problems).getTotal());
        return result;
    }

    /**
     * 查找一个具体的题目,并前往题目页面
     * @param id 对应题目的id
     * @return 题目页面视图
     * @throws PageException 未找到该题目,则抛出异常
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String findProblem(@PathVariable(value = "id") Integer id, HttpSession session, Model model) throws PageException {
        Optional<ProblemUser> problem = Optional.ofNullable(problemService.selectProblemBlobUserByPk(id))
                                                //如果是竞赛题,则需要过滤验证
                                                .filter(problemUser -> {
                                                    Integer contestId = problemUser.getContestId();
                                                    if (contestId != null && contestId != 0){
                                                        User user = userService.getCurrent();
                                                        if (user == null){
                                                            return false;
                                                        }
                                                        Integer temp = (Integer) session.getAttribute(Contest.SESSION_ACCESS);
                                                        if (temp==null) return false;
                                                        return  temp == (user.getId()+contestId);
                                                    }
                                                    return true;
                                                });

        model.addAttribute("problem",
                problem.orElseThrow(() -> new PageException(PROBLEM_NOT_EXIST)));
        return "problem";
    }


}
