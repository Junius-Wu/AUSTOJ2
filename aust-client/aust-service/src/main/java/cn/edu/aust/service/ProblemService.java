package cn.edu.aust.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import cn.edu.aust.convert.ProblemConvert;
import cn.edu.aust.common.constant.PosCode;
import cn.edu.aust.dto.ProblemDTO;
import cn.edu.aust.dto.ProblemListDTO;
import cn.edu.aust.mapper.ProblemMapper;
import cn.edu.aust.pojo.entity.ProblemDO;
import cn.edu.aust.query.ProblemPK;
import cn.edu.aust.query.ProblemQM;

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
    ProblemPK problemPK = problemMapper.queryDetail(id);
    if (Objects.isNull(problemPK)) {
      throw new PageException(PosCode.NO_PRIVILEGE.getMsg());
    }
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(problemPK, ProblemDTO.class);
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
   * 查询一个比赛的题目详情
   *
   * @param problemId 竞赛id
   * @return 查询结果
   */
  public ProblemDTO queryContestProblem(Long problemId, HttpSession session) {
    ProblemPK problemPK = problemMapper.queryContestProblem(problemId);
    if (Objects.isNull(problemPK)) {
      throw new PageException(PosCode.NO_PRIVILEGE.getMsg());
    }
    //判断是否有权查看该题目
    String curContest = (String) session.getAttribute("contest");
    if (StringUtils.isEmpty(curContest)) {
      throw new PageException(PosCode.NO_PRIVILEGE.getMsg());
    } else {
      String[] ids = StringUtils.split(curContest, ",");
      if (Arrays.binarySearch(ids, String.valueOf(problemPK.getContestId())) < 0) {
        throw new PageException(PosCode.NO_PRIVILEGE.getMsg());
      }
    }
    return ProblemConvert.pk2dto(problemPK);
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
  public PageInfo<ProblemListDTO> queryListStage(String search, Integer stage, String direction,
      Integer pageNum, Integer pageSize, boolean isCatelog) {
    //封装查询条件
    ProblemQM problemQM = new ProblemQM();
    problemQM.setDirection(direction);
    problemQM.setSearch(search);
    problemQM.setStage(stage);
    //查询转换
    Page<ProblemPK> problemPCS =
        PageHelper.startPage(pageNum, pageSize)
            .doSelectPage(
                () -> {
                  if (isCatelog) {
                    problemMapper.queryListCatelog(problemQM);
                  } else {
                    problemMapper.queryListStage(problemQM);
                  }
                }
            );
    PageInfo<ProblemListDTO> pageInfo = new PageInfo<>();
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
  public List<ProblemListDTO> queryContest(Long contest) {
    List<ProblemPK> result = problemMapper.queryContest(contest);
    return ProblemConvert.pk2ListDto(result);
  }
}
