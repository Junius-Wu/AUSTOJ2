package cn.edu.aust.service;


import cn.edu.aust.common.entity.Notify;

/**
 * @author Niu Li
 * @date 2016/9/6
 */
public interface NotifyService {
    int deleteByPrimaryKey(Integer id);

    int insert(Notify record);

    int insertSelective(Notify record);

    Notify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Notify record);

    int updateByPrimaryKey(Notify record);
}
