package cn.edu.aust.convert;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;
import java.util.Objects;

import cn.edu.aust.dto.ProblemDTO;
import cn.edu.aust.dto.ProblemListDTO;
import cn.edu.aust.pojo.entity.ProblemDO;
import cn.edu.aust.query.ProblemPK;

/**
 * 题目包装类
 *
 * @author Niu Li
 * @since 2017/2/26
 */
public final class ProblemConvert {

  private static final ModelMapper modelMapper = new ModelMapper();



  /**
   * 集合转换
   *
   * @param problemPKS 要转换的实体类
   * @return 结果
   */
  public static List<ProblemListDTO> pk2ListDto(List<ProblemPK> problemPKS) {
    return modelMapper.map(problemPKS,
                           new TypeToken<List<ProblemListDTO>>() {}.getType());
  }

  /**
   * 转化单个
   * @param problemPK 要转换的实体类
   * @return 转换结果
   */
  public static ProblemDTO pk2dto(ProblemPK problemPK){
    return modelMapper.map(problemPK,ProblemDTO.class);
  }

  /**
   * do转dto
   * @param problemDO do实体
   * @return dto实体
   */
  public static ProblemDTO do2dto(ProblemDO problemDO){
    if (Objects.isNull(problemDO)){
      return null;
    }
    return modelMapper.map(problemDO,ProblemDTO.class);
  }

}
