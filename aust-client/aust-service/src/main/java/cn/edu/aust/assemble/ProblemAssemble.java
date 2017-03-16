package cn.edu.aust.assemble;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

import cn.edu.aust.dto.ProblemDTO;
import cn.edu.aust.dto.ProblemListDTO;
import cn.edu.aust.query.ProblemDOPC;

/**
 * 题目包装类
 *
 * @author Niu Li
 * @since 2017/2/26
 */
public final class ProblemAssemble {

  private static final ModelMapper modelMapper = new ModelMapper();

  /**
   * 集合转换
   *
   * @param problemPCS 要转换的实体类
   * @return 结果
   */
  public static List<ProblemListDTO> assembleList(List<ProblemDOPC> problemPCS) {
    return modelMapper.map(problemPCS,
                           new TypeToken<List<ProblemListDTO>>() {
                           }.getType());
  }

  /**
   * 转化单个
   * @param problemPC 要转换的实体类
   * @return 转换结果
   */
  public static ProblemDTO assemble(ProblemDOPC problemPC){
    return modelMapper.map(problemPC,ProblemDTO.class);
  }
}
