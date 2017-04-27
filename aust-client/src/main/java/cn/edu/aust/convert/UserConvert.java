package cn.edu.aust.convert;

import org.modelmapper.ModelMapper;

import java.util.Objects;

import cn.edu.aust.dto.UserDTO;
import cn.edu.aust.pojo.entity.UserDO;

/**
 * 用户信息转换工具
 * @author Niu Li
 * @since 2017/3/15
 */
public class UserConvert {

  private static final ModelMapper modelMapper = new ModelMapper();

  /**
   * 用户数据模型转换为传输类型
   * @param userDO 数据模型
   * @return 传输类型
   */
  public static UserDTO user2UserDTO(UserDO userDO){
    if (Objects.isNull(userDO)){
      return null;
    }
    return modelMapper.map(userDO, UserDTO.class);
  }
}
