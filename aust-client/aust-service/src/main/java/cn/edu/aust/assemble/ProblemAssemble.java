package cn.edu.aust.assemble;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

import cn.edu.aust.dto.ProblemDTO;
import cn.edu.aust.dto.ProblemListDTO;
import cn.edu.aust.query.ProblemPK;

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
   * @param problemPKS 要转换的实体类
   * @return 结果
   */
  public static List<ProblemListDTO> assembleList(List<ProblemPK> problemPKS) {
    return modelMapper.map(problemPKS,
                           new TypeToken<List<ProblemListDTO>>() {
                           }.getType());
  }

  /**
   * 转化单个
   * @param problemPK 要转换的实体类
   * @return 转换结果
   */
  public static ProblemDTO assemble(ProblemPK problemPK){
    return modelMapper.map(problemPK,ProblemDTO.class);
  }
}
