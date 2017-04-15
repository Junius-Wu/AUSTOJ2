package cn.edu.aust.convert;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;
import java.util.Objects;

import cn.edu.aust.dto.ProblemDTO;
import cn.edu.aust.dto.ProblemBasicDTO;
import cn.edu.aust.pojo.entity.ProblemDO;
import cn.edu.aust.query.ProblemPO;

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
   * @param problemPOS 要转换的实体类
   * @return 结果
   */
  public static List<ProblemBasicDTO> pk2ListDto(List<ProblemPO> problemPOS) {
    return modelMapper.map(problemPOS,
                           new TypeToken<List<ProblemBasicDTO>>() {}.getType());
  }

  /**
   * 转化单个
   * @param problemPO 要转换的实体类
   * @return 转换结果
   */
  public static ProblemDTO pk2dto(ProblemPO problemPO){
    return modelMapper.map(problemPO,ProblemDTO.class);
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
