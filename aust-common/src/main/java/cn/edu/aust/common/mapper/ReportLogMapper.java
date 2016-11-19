package cn.edu.aust.common.mapper;

import org.apache.ibatis.annotations.Param;

import cn.edu.aust.common.entity.ReportLog;

public interface ReportLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReportLog record);

    int insertSelective(ReportLog record);

    ReportLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReportLog record);

    int updateByPrimaryKey(ReportLog record);

    /**
     * 根据三个参数决定唯一的用户点赞记录
     * @param type 类型
     * @param other_id id
     * @param user_id 用户id
     * @return 存在返回实例,不存在返回null
     */
    ReportLog selectByType(@Param("type") Byte type,
                           @Param("other_id") Integer other_id,
                           @Param("user_id") Integer user_id);
}