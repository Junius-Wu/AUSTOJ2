package cn.edu.aust.service;

import com.github.pagehelper.PageException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.aust.common.constant.PosCode;
import cn.edu.aust.dto.ProblemDTO;
import cn.edu.aust.dto.ProblemListDTO;
import cn.edu.aust.mapper.ProblemMapper;
import cn.edu.aust.pojo.entity.Problem;
import cn.edu.aust.query.ProblemPC;
import cn.edu.aust.query.ProblemQM;

/**
 * 题目的service
 *
 * @author Niu Li
 * @date 2017/1/29
 */
@Service
public class ProblemService extends BaseService<Problem> {
    @Autowired
    private ProblemMapper problemMapper;

    /**
     * 查询一个题目的详情
     * @param id 主键
     * @return 结果
     */
    public ProblemDTO queryDetail(Long id){
        ProblemPC problemPC = problemMapper.queryDetail(id);
        if (problemPC == null){
            throw new PageException(PosCode.NO_PRIVILEGE.getMsg());
        }
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(problemPC,ProblemDTO.class);
    }
    /**
     * 查询用于列表展示的题目
     *
     * @param search    搜索内容
     * @param stage     对应阶段
     * @param direction 排序方向,针对id字段
     * @param offset 分页参数
     * @param limit 分页参数
     * @return DTO实体
     */
    public PageInfo<ProblemListDTO> queryListStage(String search, Integer stage, String direction, Integer offset, Integer limit) {
        //封装查询条件
        ProblemQM problemQM = new ProblemQM();
        problemQM.setDirection(direction);
        problemQM.setSearch(search);
        problemQM.setStage(stage);
        //查询转换
        PageInfo<ProblemPC> problemPCS =
                PageHelper.offsetPage(offset, limit)
                          .doSelectPageInfo(
                                  () -> {
                                      problemMapper.queryListStage(problemQM);
                                  }
                          );

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(problemPCS, new TypeToken<PageInfo<ProblemListDTO>>() {}.getType());
    }
}
