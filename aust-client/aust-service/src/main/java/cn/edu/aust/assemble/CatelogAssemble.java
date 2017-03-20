package cn.edu.aust.assemble;

import org.modelmapper.ModelMapper;

import java.util.Objects;

import cn.edu.aust.dto.CatelogDTO;
import cn.edu.aust.pojo.entity.CatelogDO;

/**
 * 目录包装转换
 * @author Niu Li
 * @since 2017/3/20
 */
public class CatelogAssemble {

  private static final ModelMapper modelMapper = new ModelMapper();

  /**
   * do转换为dto
   * @param catelogDO do实体
   * @return dto实体
   */
  public static CatelogDTO do2dto(CatelogDO catelogDO){
    if (Objects.isNull(catelogDO)){
      return null;
    }
    return modelMapper.map(catelogDO,CatelogDTO.class);
  }
}
