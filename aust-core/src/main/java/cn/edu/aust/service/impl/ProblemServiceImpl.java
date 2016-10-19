package cn.edu.aust.service.impl;


import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

import cn.edu.aust.common.util.PageAble;
import cn.edu.aust.common.entity.Problem;
import cn.edu.aust.common.entity.ProblemBLOBs;
import cn.edu.aust.common.mapper.ProblemMapper;
import cn.edu.aust.service.ProblemService;

/**
 * @author Niu Li
 * @date 2016/10/5
 */
@Service("problemServiceImpl")
public class ProblemServiceImpl implements ProblemService {
    @Resource
    private ProblemMapper problemMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return problemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ProblemBLOBs record) {
        return problemMapper.insert(record);
    }

    @Override
    public int insertSelective(ProblemBLOBs record) {
        return problemMapper.insertSelective(record);
    }

    @Override
    public ProblemBLOBs selectByPrimaryKey(Integer id) {
        return problemMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ProblemBLOBs record) {
        return problemMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(ProblemBLOBs record) {
        return problemMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(Problem record) {
        return problemMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Problem> selectWithCriteria(PageAble pageAble) {
        return problemMapper.selectWithCriteria(pageAble);
    }
}
