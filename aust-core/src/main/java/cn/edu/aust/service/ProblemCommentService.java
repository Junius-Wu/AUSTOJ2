package cn.edu.aust.service;

import java.util.List;

import cn.edu.aust.common.entity.ProblemComment;
import cn.edu.aust.common.entity.User;
import cn.edu.aust.common.entity.pojo.ProblemCommentUser;

/**
 * 题目评论对应的服务
 * @author Niu Li
 * @date 2016/11/12
 */
public interface ProblemCommentService {
    /**
     * 根据主键删除评论,需要用户校验
     * @param id 该评论id
     * @param user 该用户
     * @return true成功
     */
    boolean deleteByPKAndUser(Integer id, User user);

    /**
     * 插入一条评论
     * @param problem_id 对应题目id
     * @param content 内容
     * @param isReply 是否是回复
     * @param friend_id 对方id
     * @param user 当前用户
     * @return true成功
     */
    boolean insert(Integer problem_id,String content, Boolean isReply, Integer friend_id,User user);

    int insertSelective(ProblemComment record);

    ProblemComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProblemComment record);

    /**
     * 根据主键修改该评论
     * @param id 该评论id
     * @param content 修改内容
     * @param user 用户id
     * @return true成功
     */
    boolean updateByPKWithBLOBs(Integer id,String content, User user);

    int updateByPrimaryKey(ProblemComment record);

    /**
     * 根据题目查找直接显示的根评论
     * @param problem_id 题目id
     * @param belowStatus 该评论状态,查询条件小于该值
     * @return 查询集合
     */
    List<ProblemCommentUser> selectToShow(Integer problem_id, Integer belowStatus,Boolean isOrder);

    /**
     * 根据根节点id查找出旗下评论
     * @param rootId 根节点id
     * @return 评论集合
     */
    List<ProblemCommentUser> selectByRootId(Integer rootId,Integer belowStatus);
}
