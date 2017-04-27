package cn.edu.aust.convert;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import cn.edu.aust.pojo.entity.ProblemDO;

/**
 * 题目包装类
 *
 * @author Niu Li
 * @since 2017/2/26
 */
public final class ProblemConvert {

  private static final ModelMapper modelMapper = new ModelMapper();

  static{
    modelMapper.addMappings(new PropertyMap<ProblemDO, ProblemDTO>() {
      @Override
      protected void configure() {
       skip(source.getType(),destination.getType());
      }
    });
  }

}
