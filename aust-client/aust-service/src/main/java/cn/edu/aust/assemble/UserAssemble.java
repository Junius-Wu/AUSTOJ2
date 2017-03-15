package cn.edu.aust.assemble;

import org.modelmapper.ModelMapper;

import cn.edu.aust.dto.UserDTO;
import cn.edu.aust.pojo.entity.User;

/**
 * 用户信息转换工具
 * @author Niu Li
 * @since 2017/3/15
 */
public class UserAssemble {

  private static final ModelMapper modelMapper = new ModelMapper();

  /**
   * 用户数据模型转换为传输类型
   * @param user 数据模型
   * @return 传输类型
   */
  public static UserDTO user2UserDTO(User user){
    return modelMapper.map(user,UserDTO.class);
  }
}
