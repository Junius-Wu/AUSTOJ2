package cn.edu.aust.mapper;

import cn.edu.aust.entity.Notify;

public interface NotifyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Notify record);

    int insertSelective(Notify record);

    Notify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Notify record);

    int updateByPrimaryKey(Notify record);
}