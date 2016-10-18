package cn.edu.aust.service;


import java.util.List;

import cn.edu.aust.common.entity.Tag;

/**
 * @author Niu Li
 * @date 2016/9/6
 */
public interface TagService {

    int deleteByPrimaryKey(Integer id);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

    List<Tag> selectList(Integer value);
}
