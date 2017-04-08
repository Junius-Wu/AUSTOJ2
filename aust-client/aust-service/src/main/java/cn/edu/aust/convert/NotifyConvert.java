package cn.edu.aust.convert;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

import cn.edu.aust.dto.NotifyDTO;
import cn.edu.aust.pojo.entity.NotifyDO;

/**
 * 通知转换
 * @author Niu Li
 * @since 2017/4/8
 */
public class NotifyConvert {

  private static final ModelMapper modelMapper = new ModelMapper();

  /**
   * 批量model到dto
   * @param notifyDOS models
   * @return dtos
   */
  public static List<NotifyDTO> notify2DTO(List<NotifyDO> notifyDOS){
    if (CollectionUtils.isEmpty(notifyDOS)){
      return Collections.emptyList();
    }
    return modelMapper.map(notifyDOS,new TypeToken<List<NotifyDTO>>(){}.getType());
  }
}
