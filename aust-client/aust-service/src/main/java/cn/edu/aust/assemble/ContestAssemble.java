package cn.edu.aust.assemble;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.Date;

import cn.edu.aust.common.util.DateUtil;
import cn.edu.aust.convert.Date2StrConvert;
import cn.edu.aust.dto.ContestDTO;
import cn.edu.aust.pojo.entity.Contest;

/**
 * contest转换类
 * @author Niu Li
 * @since 2017/2/26
 */
public class ContestAssemble {

  private static final ModelMapper modelMapper = new ModelMapper();

  static {
    //本类的转换器
    Converter<Byte,String> typeConvert = new AbstractConverter<Byte, String>() {
      @Override
      protected String convert(Byte aByte) {
        return aByte == 1 ?"校内赛":"公开赛";
      }
    };
    modelMapper.addConverter(Date2StrConvert.date2Str);

    modelMapper.addMappings(new PropertyMap<Contest, ContestDTO>() {
      @Override
      protected void configure() {
        using(typeConvert).map(source.getType(),destination.getTypeName());
      }
    });
  }

  /**
   * 转换单个
   * @param contest 该竞赛
   * @return 转换后的DTO
   */
  public static ContestDTO assemble(Contest contest){
    return modelMapper.map(contest,ContestDTO.class);
  }
}
