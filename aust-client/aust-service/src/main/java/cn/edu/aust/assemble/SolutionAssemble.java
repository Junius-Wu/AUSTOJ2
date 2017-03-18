package cn.edu.aust.assemble;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

import cn.edu.aust.dto.SolutionDTO;
import cn.edu.aust.pojo.entity.SolutionDO;

/**
 * 提交表转换
 * @author Niu Li
 * @since 2017/3/18
 */
public class SolutionAssemble {

  private static final ModelMapper modelMapper = new ModelMapper();

  /**
   * do转dto
   * @param solutionDOS do集合
   * @return dto集合
   */
  public static List<SolutionDTO> do2dto(List<SolutionDO> solutionDOS){
    return modelMapper.map(solutionDOS,new TypeToken<List<SolutionDTO>>(){}.getType());
  }
}
