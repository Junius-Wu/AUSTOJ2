package cn.edu.aust.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

import cn.edu.aust.common.entity.ProblemComment;
import cn.edu.aust.common.entity.pojo.ProblemCommentUser;
import cn.edu.aust.common.mapper.ProblemCommentMapper;
import cn.edu.aust.common.mybatis.Filter;
import cn.edu.aust.common.mybatis.Order;
import cn.edu.aust.common.mybatis.QueryParams;
import cn.edu.aust.service.ProblemCommentService;

/**
 * 题目评论对应的服务
 * @author Niu Li
 * @date 2016/11/12
 */
@Service("problemCommentServiceImpl")
public class ProblemCommentServiceImpl implements ProblemCommentService {
    @Resource
    private ProblemCommentMapper problemCommentMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return problemCommentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ProblemComment record) {
        return problemCommentMapper.insert(record);
    }

    @Override
    public int insertSelective(ProblemComment record) {
        return problemCommentMapper.insertSelective(record);
    }

    @Override
    public ProblemComment selectByPrimaryKey(Integer id) {
        return problemCommentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ProblemComment record) {
        return problemCommentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(ProblemComment record) {
        return problemCommentMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(ProblemComment record) {
        return problemCommentMapper.updateByPrimaryKey(record);
    }

    public List<ProblemCommentUser> selectToShow(Integer problem_id, Integer belowStatus){
        QueryParams queryParams = new QueryParams();
        queryParams.and(Filter.isNull("root_id")); //为根节点
        queryParams.and(Filter.lt("status",belowStatus)); //状态小于此值
        queryParams.and(Filter.eq("problem_id",problem_id)); //题目id
        queryParams.order(Order.descOrder("likecount"));//按点赞排序
        return problemCommentMapper.selectParamsWithUser(queryParams);
    }

    @Override
    public List<ProblemCommentUser> selectByRootId(Integer rootId,Integer belowStatus) {
        QueryParams queryParams = new QueryParams();
        queryParams.and(Filter.eq("root_id", rootId)); //题目id
        queryParams.and(Filter.lt("status",belowStatus)); //状态小于此值
        queryParams.order(Order.descOrder("likecount"));//按点赞排序
        return problemCommentMapper.selectParamsWithUser(queryParams);
    }

}
