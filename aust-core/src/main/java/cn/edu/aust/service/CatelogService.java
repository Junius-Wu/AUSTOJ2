package cn.edu.aust.service;

import cn.edu.aust.entity.Catelog;

/**
 * @author Niu Li
 * @date 2016/9/6
 */
public interface CatelogService {
    int deleteByPrimaryKey(Integer id);

    int insert(Catelog record);

    int insertSelective(Catelog record);

    Catelog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Catelog record);

    int updateByPrimaryKey(Catelog record);
}
