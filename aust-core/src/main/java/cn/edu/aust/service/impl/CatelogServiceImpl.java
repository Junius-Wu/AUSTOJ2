package cn.edu.aust.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

import cn.edu.aust.entity.Catelog;
import cn.edu.aust.mapper.CatelogMapper;
import cn.edu.aust.service.CatelogService;

/**
 * @author Niu Li
 * @date 2016/10/5
 */
@Service("catelogServiceImpl")
public class CatelogServiceImpl implements CatelogService {
    @Resource
    private CatelogMapper catelogMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return catelogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Catelog record) {
        return catelogMapper.insert(record);
    }

    @Override
    public int insertSelective(Catelog record) {
        return catelogMapper.insertSelective(record);
    }

    @Override
    public Catelog selectByPrimaryKey(Integer id) {
        return catelogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Catelog record) {
        return catelogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Catelog record) {
        return catelogMapper.updateByPrimaryKey(record);
    }
    @Override
    public List<Catelog> selectList(Integer type){
        return catelogMapper.selectToShow(type);
    }
}
