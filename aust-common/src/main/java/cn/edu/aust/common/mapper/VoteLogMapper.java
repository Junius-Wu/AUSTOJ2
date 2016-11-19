package cn.edu.aust.common.mapper;

import org.apache.ibatis.annotations.Param;

import cn.edu.aust.common.entity.VoteLog;

public interface VoteLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VoteLog record);

    int insertSelective(VoteLog record);

    VoteLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VoteLog record);

    int updateByPrimaryKey(VoteLog record);

    /**
     * 根据三个参数决定唯一的用户点赞记录
     * @param type 类型
     * @param other_id id
     * @param user_id 用户id
     * @return 存在返回实例,不存在返回null
     */
    VoteLog selectByType(@Param("type") Byte type,
                         @Param("other_id") Integer other_id,
                         @Param("user_id") Integer user_id);
}