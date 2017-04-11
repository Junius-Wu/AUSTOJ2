package cn.edu.aust.vo;

import com.google.common.collect.Lists;

import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import cn.edu.aust.dto.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 用户排名显示的VO信息,包括首页展示和排名页面
 * @author Niu Li
 * @since 2017/3/16
 */
@Data
@NoArgsConstructor
@ToString
public class UserRankVO {

  private Long id;

  private String email;

  private String avatar;

  private String nickname;

  private String honor;

  private String motto;

  private String blog;

  private Integer submit;

  private Integer solved;

  private String acRate;

  public static List<UserRankVO> assemble(List<UserDTO> userDTOS){
    if (CollectionUtils.isEmpty(userDTOS)){
      return Collections.emptyList();
    }
    List<UserRankVO> result = Lists.newArrayList();
    userDTOS.forEach(x -> {
      result.add(assemble(x));
    });
    return result;
  }

  private static UserRankVO assemble(UserDTO x) {
    UserRankVO rankVO = new UserRankVO();
    rankVO.setId(x.getId());
    rankVO.setAvatar(x.getAvatar());
    rankVO.setEmail(x.getEmail());
    rankVO.setNickname(x.getNickname());
    rankVO.setHonor(x.getHonor());
    rankVO.setMotto(x.getMotto());
    rankVO.setBlog(x.getBlog());
    rankVO.setSubmit(x.getSubmit());
    rankVO.setSolved(x.getSolved());
    if (Objects.isNull(x.getSolved()) || Objects.isNull(x.getSubmit())){
      rankVO.setAcRate("0%");
    }else {
      double submit = x.getSubmit();
      if (submit == 0){
        submit = 1;
      }
      rankVO.setAcRate(String.format("%.2f",x.getSolved()/submit)+"%");
    }
    return rankVO;
  }
}
