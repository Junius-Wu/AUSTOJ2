package cn.edu.aust.vo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import cn.edu.aust.common.constant.user.UserStatus;
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

  private String intro;

  private String blog;

  private String language;

  /**
   * 用户历史解决题目
   */
  List<Integer> ACProblems;
  /**
   * 用户最近解决的题目,数量为5
   */
  List<Integer> ACRecents;

  private Integer ACTotal;

  private Long rank;

  private Boolean isEmail;

  public static UserInfoVO assemble(UserDTO userDTO, List<Integer> ACProblems,Long rank) {
    UserInfoVO infoVo = new UserInfoVO();
    infoVo.setId(userDTO.getId());
    infoVo.setEmail(userDTO.getEmail());
    infoVo.setAvatar(userDTO.getAvatar());
    infoVo.setNickname(userDTO.getNickname());
    infoVo.setIntro(userDTO.getIntro());
    infoVo.setBlog(userDTO.getBlog());
    infoVo.setLanguage(StringUtils.equals("C++",userDTO.getLanguage())
        ?"C2":userDTO.getLanguage());
    infoVo.setIsEmail(!(userDTO.getStatus() == UserStatus.WAIT4EMAIL_CHECK.value));
    if (!CollectionUtils.isEmpty(ACProblems)) {
      infoVo.setACProblems(ACProblems);
      infoVo.setACRecents(ACProblems.stream().limit(6).collect(Collectors.toList()));
      infoVo.setACTotal(ACProblems.size());
    }
    if (Objects.nonNull(rank)){
      infoVo.setRank(rank+1);
    }else {
      infoVo.setRank(-1L);
    }

    return infoVo;
  }
}
