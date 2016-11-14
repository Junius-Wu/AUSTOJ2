package cn.edu.aust.service;

import java.util.List;

import cn.edu.aust.common.entity.ProblemComment;
import cn.edu.aust.common.entity.pojo.ProblemCommentUser;

/**
 * 题目评论对应的服务
 * @author Niu Li
 * @date 2016/11/12
 */
public interface ProblemCommentService {

    int deleteByPrimaryKey(Integer id);

    int insert(ProblemComment record);

    int insertSelective(ProblemComment record);

    ProblemComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProblemComment record);

    int updateByPrimaryKeyWithBLOBs(ProblemComment record);

    int updateByPrimaryKey(ProblemComment record);

    /**
     * 根据题目查找直接显示的根评论
     * @param problem_id 题目id
     * @param belowStatus 该评论状态,查询条件小于该值
     * @return 查询集合
     */
    List<ProblemCommentUser> selectToShow(Integer problem_id, Integer belowStatus);
}
