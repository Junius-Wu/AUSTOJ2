package cn.edu.aust.service.impl;


import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

import cn.edu.aust.common.entity.Tag;
import cn.edu.aust.common.mapper.TagMapper;
import cn.edu.aust.service.TagService;

/**
 * @author Niu Li
 * @date 2016/10/5
 */
@Service("tagServiceImpl")
public class TagServiceImpl implements TagService {

    @Resource
    private TagMapper tagMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tagMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Tag record) {
        return tagMapper.insert(record);
    }

    @Override
    public int insertSelective(Tag record) {
        return tagMapper.insertSelective(record);
    }

    @Override
    public Tag selectByPrimaryKey(Integer id) {
        return tagMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Tag record) {
        return tagMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Tag record) {
        return tagMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Tag> selectList(Integer value) {
        return tagMapper.selectList(value);
    }

    @Override
    public boolean insertAndFlush(String tagName) {
        int result = 0;
        Tag tag = tagMapper.selectByName(tagName);
        if (tag == null){
            tag = new Tag();
            tag.setTag(tagName);
            tag.setCount(0);
            result = tagMapper.insertSelective(tag);
        }else {
            tag.setCount(tag.getCount() + 1);
            result = tagMapper.updateByPrimaryKey(tag);
        }
        return result > 0;
    }
}
