package cn.edu.aust.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import cn.edu.aust.convert.ProblemConvert;
import cn.edu.aust.dto.ProblemDTO;
import cn.edu.aust.dto.ProblemBasicDTO;
import cn.edu.aust.mapper.ProblemMapper;
import cn.edu.aust.pojo.entity.ProblemDO;
import cn.edu.aust.query.ProblemPO;
import cn.edu.aust.query.ProblemQuery;

/**
 * 题目的service
 *
 * @author Niu Li
 * @date 2017/1/29
 */
@Service
public class ProblemService {
  @Resource
  private ProblemMapper problemMapper;

  /**
   * 查询一个题目的详情
   * @param id 主键
   * @return 结果
   */
  public ProblemDTO findDetail(Long id) {
    ProblemPO problemPO = problemMapper.queryDetail(id);
    if (Objects.isNull(problemPO)) {
      return null;
    }
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(problemPO, ProblemDTO.class);
  }

  /**
   * 查询题目基本信息
   * @param id 题目id
   * @return 查询实体
   */
  public ProblemDTO findBasicById(Long id){
    ProblemDO problemDO = problemMapper.selectByPrimaryKey(id);
    return ProblemConvert.do2dto(problemDO);
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
  public PageInfo<ProblemBasicDTO> queryListStage(String search, Integer stage, String direction,
      Integer pageNum, Integer pageSize, boolean isCatelog) {
    //封装查询条件
    ProblemQuery problemQuery = new ProblemQuery();
    problemQuery.setDirection(direction);
    problemQuery.setSearch(search);
    problemQuery.setStage(stage);
    //查询转换
    Page<ProblemPO> problemPCS =
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
    PageInfo<ProblemBasicDTO> pageInfo = new PageInfo<>();
    pageInfo.setTotal(problemPCS.getTotal());
    pageInfo.setList(ProblemConvert.pk2ListDto(problemPCS.getResult()));
    return pageInfo;
  }

  /**
   * 查询一个竞赛下面的比赛
   *
   * @param contest 该竞赛
   * @return 查询结果
   */
  public List<ProblemBasicDTO> queryContest(Long contest) {
    List<ProblemPO> result = problemMapper.queryContest(contest);
    return ProblemConvert.pk2ListDto(result);
  }
}
