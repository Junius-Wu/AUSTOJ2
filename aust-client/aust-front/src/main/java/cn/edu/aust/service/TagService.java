package cn.edu.aust.service;


import java.util.List;

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

    /**
     * 保存或者更新一个标签
     * @param tagName 标签名
     * @return true成功
     */
    boolean insertAndFlush(String tagName);
}
