package cn.edu.aust.common.mapper;

import java.util.List;

import cn.edu.aust.common.entity.ProblemComment;
import cn.edu.aust.common.entity.pojo.ProblemCommentUser;
import cn.edu.aust.common.mybatis.QueryParams;

public interface ProblemCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProblemComment record);

    int insertSelective(ProblemComment record);

    ProblemComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProblemComment record);

    int updateByPrimaryKeyWithBLOBs(ProblemComment record);

    int updateByPrimaryKey(ProblemComment record);

    /**
     * 根据QueryParam参数动态查询
     * @return 查询结果
     */
    List<ProblemComment> selectParams(QueryParams queryParams);

    /**
     * 根据QueryParam参数动态查询
     * @return 查询结果,加关联用户
     */
    List<ProblemCommentUser> selectParamsWithUser(QueryParams queryParams);
}