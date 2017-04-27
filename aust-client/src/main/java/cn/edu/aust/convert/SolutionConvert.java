package cn.edu.aust.convert;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;

import java.util.List;

import cn.edu.aust.utilconvert.JudgeCodeConvent;
import cn.edu.aust.dto.SolutionDTO;
import cn.edu.aust.pojo.entity.SolutionDO;

/**
 * 提交表转换
 * @author Niu Li
 * @since 2017/3/18
 */
public class SolutionConvert {

  private static final ModelMapper modelMapper = new ModelMapper();

  static{
    modelMapper.addMappings(new PropertyMap<SolutionDO, SolutionDTO>() {
      @Override
      protected void configure() {
       using(JudgeCodeConvent.judgeCodeConvent).map(source.getVerdict(),destination.getVerdict());
      }
    });
  }
  /**
   * do转dto
   * @param solutionDOS do集合
   * @return dto集合
   */
  public static List<SolutionDTO> do2dto(List<SolutionDO> solutionDOS){
    return modelMapper.map(solutionDOS,new TypeToken<List<SolutionDTO>>(){}.getType());
  }
}
