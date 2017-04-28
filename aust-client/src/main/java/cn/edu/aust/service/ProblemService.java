package cn.edu.aust.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import cn.edu.aust.dto.BaseProblemDTO;
import cn.edu.aust.entity.ProblemQuery;
import cn.edu.aust.mapper.ProblemMapper;
import cn.edu.aust.pojo.entity.ProblemDO;

/**
 * 题目的service
 *
 * @author Niu Li
 * @since  2017/1/29
 */
@Service
public class ProblemService {
  @Resource
  private ProblemMapper problemMapper;
  @Resource
  private ModelMapper modelMapper;
  /**
   * 查询一个题目的详情
   * @param id 主键
   * @return 结果
   */
  public ProblemDO findDetail(Long id) {
    return problemMapper.findDetail(id);
  }

  /**
   * 查询题目基本信息
   * @param id 题目id
   * @return 查询实体
   */
  public BaseProblemDTO findBasicById(Long id){
    Optional<ProblemDO> problemDO = Optional.ofNullable(problemMapper.findBasic(id));
    return problemDO.map(x -> modelMapper.map(x,BaseProblemDTO.class)).orElse(null);
  }

  /**
   * 查询用于列表展示的题目
   *
   * @param search    搜索内容
   * @param stage     对应阶段
   * @param direction 排序方向,针对id字段
   * @param pageNum    分页参数
   * @param pageSize     分页参数
   * @param isCatelog 是否为目录,true时stage参数为目录id
   * @return DTO实体
   */
  public PageInfo<BaseProblemDTO> queryListStage(String search, Integer stage, String direction,
      Integer pageNum, Integer pageSize, boolean isCatelog) {
    //封装查询条件
    ProblemQuery problemQuery = new ProblemQuery();
    problemQuery.setDirection(direction);
    problemQuery.setSearch(search);
    problemQuery.setStage(stage);
    //查询转换
    Page<ProblemDO> problems =
        PageHelper.startPage(pageNum, pageSize)
            .doSelectPage(
                () -> {
                  if (isCatelog) {
                    problemMapper.queryListCatelog(problemQuery);
                  } else {
                    problemMapper.queryListStage(problemQuery);
                  }
                }
            );
    PageInfo<BaseProblemDTO> pageInfo = new PageInfo<>();
    pageInfo.setTotal(problems.getTotal());
    pageInfo.setList(modelMapper.map(problems.getResult(),
        new TypeToken<List<BaseProblemDTO>>(){}.getType()));
    return pageInfo;
  }

}
