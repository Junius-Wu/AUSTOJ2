package cn.edu.aust.common.mapper;


import java.util.List;

import cn.edu.aust.common.entity.Tag;

public interface TagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

    List<Tag> selectList(Integer value);

    /**
     * 根据tag名称查找
     * @param tagName 标签名称
     * @return 查找结果
     */
    Tag selectByName(String tagName);
}