package cn.edu.aust.service.impl;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import cn.edu.aust.Setting;
import cn.edu.aust.common.constant.CommentConstant;
import cn.edu.aust.common.entity.Problem;
import cn.edu.aust.common.entity.ProblemComment;
import cn.edu.aust.common.entity.User;
import cn.edu.aust.common.entity.pojo.ProblemCommentUser;
import cn.edu.aust.common.mapper.ProblemCommentMapper;
import cn.edu.aust.common.mapper.ProblemMapper;
import cn.edu.aust.common.mapper.UserMapper;
import cn.edu.aust.common.mybatis.Filter;
import cn.edu.aust.common.mybatis.Order;
import cn.edu.aust.common.mybatis.QueryParams;
import cn.edu.aust.common.util.StringUtils;
import cn.edu.aust.service.ProblemCommentService;
import cn.edu.aust.util.SystemUtil;

/**
 * 题目评论对应的服务
 * @author Niu Li
 * @date 2016/11/12
 */
@Service("problemCommentServiceImpl")
public class ProblemCommentServiceImpl implements ProblemCommentService {
    @Resource
    private ProblemCommentMapper problemCommentMapper;
    @Resource
    private ProblemMapper problemMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public boolean deleteByPKAndUser(Integer id, User user) {
        final int[] result = new int[1];
        Optional<ProblemComment> comment = Optional.ofNullable(problemCommentMapper.selectByPrimaryKey(id));
        comment.filter(problemComment -> problemComment.getUserId().equals(user.getId()))
               .ifPresent(problemComment->
               {
                   result[0] =problemCommentMapper.deleteByPrimaryKey(id);//删除该评论
                   problemCommentMapper.deleteByRootId(id);//删除该评论下的评论
               });
        return result[0]>0;
    }

    @Override
    public boolean insert(Integer problem_id,String content, Boolean isReply, Integer friend_id,User user) {
        //检查是否开启评论
        Setting setting = SystemUtil.getSetting();
        if (!setting.isIsCommentEnabled()) return false;

        Problem problem = problemMapper.selectBaseByPk(problem_id);
        if (problem == null){
            return false;
        }

        ProblemComment comment = new ProblemComment();
        comment.setProblemId(problem_id);
        comment.setContent(content);
        if (isReply){
            User friend = userMapper.selectByPrimaryKey(friend_id);
            if (friend == null){
                return false;
            }
        }
        comment.setLikecount(0);
        comment.setCreatedate(new Date());
        comment.setModifydate(new Date());
        comment.setFirendId(friend_id);
        comment.setUserId(user.getId());
        comment.setStatus((setting.isIsCommentChecked()? CommentConstant.AUDIT:CommentConstant.NORMAL));
        int i = problemCommentMapper.insertSelective(comment);
        return i>0;
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
    public boolean updateByPKWithBLOBs(Integer id,String content, User user) {
        if (StringUtils.isEmpty(content)) return false;
        final int[] result = new int[1];
        Optional<ProblemComment> comment = Optional.ofNullable(problemCommentMapper.selectByPrimaryKey(id));
        //顶层评论才允许修改
        comment.filter(problemComment -> problemComment.getRootId()==null && problemComment.getUserId().equals(user.getId()))
                .ifPresent(problemComment -> {
                    problemComment.setContent(content);
                    problemComment.setModifydate(new Date());
                    result[0] = problemCommentMapper.updateByPrimaryKeyWithBLOBs(problemComment);
                });
        return result[0]>0;
    }

    @Override
    public int updateByPrimaryKey(ProblemComment record) {
        return problemCommentMapper.updateByPrimaryKey(record);
    }

    public List<ProblemCommentUser> selectToShow(Integer problem_id, Integer belowStatus,Boolean isOrder){
        QueryParams queryParams = new QueryParams();
        queryParams.and(Filter.isNull("root_id")); //为根节点
        queryParams.and(Filter.lt("status",belowStatus)); //状态小于此值
        queryParams.and(Filter.eq("problem_id",problem_id)); //题目id
        queryParams.order(isOrder?Order.descOrder("createdate"):Order.descOrder("likecount"));//按点赞排序
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
