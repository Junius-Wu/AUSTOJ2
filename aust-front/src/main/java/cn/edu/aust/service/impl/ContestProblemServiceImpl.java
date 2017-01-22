package cn.edu.aust.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

import cn.edu.aust.common.entity.ContestProblem;
import cn.edu.aust.common.mapper.ContestProblemMapper;
import cn.edu.aust.service.ContestProblemService;

/**
 * @author Niu Li
 * @date 2016/11/26
 */
@Service("contestProblemServiceImpl")
public class ContestProblemServiceImpl implements ContestProblemService {
    @Resource
    private ContestProblemMapper contestProblemMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return contestProblemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ContestProblem record) {
        return contestProblemMapper.insert(record);
    }

    @Override
    public int insertSelective(ContestProblem record) {
        return contestProblemMapper.insertSelective(record);
    }

    @Override
    public ContestProblem selectByPrimaryKey(Integer id) {
        return contestProblemMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ContestProblem record) {
        return contestProblemMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ContestProblem record) {
        return contestProblemMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ContestProblem> selectByContestId(Integer contest_id) {
        return contestProblemMapper.selectByContestId(contest_id);
    }
}
