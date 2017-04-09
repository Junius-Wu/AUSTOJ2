package cn.edu.aust.vo;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

import cn.edu.aust.dto.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Niu Li
 * @since 2017/4/8
 */
@Data
@NoArgsConstructor
@ToString
public class UserInfoVO {

  private Long id;

  private String email;

  private String avatar;

  private String nickname;

  private String motto;
  /**
   * 用户历史解决题目
   */
  List<Integer> ACProblems;
  /**
   * 用户最近解决的题目,数量为5
   */
  List<Integer> ACRecents;

  public static UserInfoVO assemble(UserDTO userDTO, List<Integer> ACProblems) {
    UserInfoVO infoVo = new UserInfoVO();
    infoVo.setId(userDTO.getId());
    infoVo.setEmail(userDTO.getEmail());
    infoVo.setAvatar(userDTO.getAvatar());
    infoVo.setNickname(userDTO.getNickname());
    infoVo.setMotto(userDTO.getMotto());
    if (!CollectionUtils.isEmpty(ACProblems)) {
      infoVo.setACProblems(ACProblems);
      infoVo.setACRecents(ACProblems.stream().limit(6).collect(Collectors.toList()));
    }
    return infoVo;
  }
}
