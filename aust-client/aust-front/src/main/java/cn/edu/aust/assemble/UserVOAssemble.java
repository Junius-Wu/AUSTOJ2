package cn.edu.aust.assemble;

import org.modelmapper.ModelMapper;

import cn.edu.aust.dto.UserDTO;
import cn.edu.aust.vo.UserRankVO;

/**
 * todo删掉
 * 用户排名相关转换
 * @author Niu Li
 * @since 2017/3/16
 */
public class UserVOAssemble {

  private static final ModelMapper modelMapper = new ModelMapper();

  /**
   * 转换为排名实体
   */
  public static UserRankVO userDTO2RankVO(UserDTO userDTO){
    return modelMapper.map(userDTO,UserRankVO.class);
  }

}
