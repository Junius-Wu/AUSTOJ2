package cn.edu.aust.service.impl;


import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

import cn.edu.aust.common.entity.Problem;
import cn.edu.aust.common.entity.ProblemWithBLOBs;
import cn.edu.aust.common.entity.pojo.ProblemUser;
import cn.edu.aust.common.mapper.ProblemMapper;
import cn.edu.aust.common.util.PageRequest;
import cn.edu.aust.service.ProblemService;

/**
 * 题目的服务层,操作Mapper层
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
    public int insert(ProblemWithBLOBs record) {
        return problemMapper.insert(record);
    }

    @Override
    public int insertSelective(ProblemWithBLOBs record) {
        return problemMapper.insertSelective(record);
    }

    @Override
    public ProblemWithBLOBs selectByPrimaryKey(Integer id) {
        return problemMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ProblemWithBLOBs record) {
        return problemMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(ProblemWithBLOBs record) {
        return problemMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(Problem record) {
        return problemMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Problem> selectWithPageRequest(PageRequest pageRequest) {
        return problemMapper.selectWithPageRequest(pageRequest);
    }

    @Override
    public ProblemUser selectProblemBlobUserByPk(Integer id) {
        return problemMapper.selectProblemBlobUserByPk(id);
    }
    @Override
    public Problem selectBaseByPk(Integer id){
        return problemMapper.selectBaseByPk(id);
    }
}
