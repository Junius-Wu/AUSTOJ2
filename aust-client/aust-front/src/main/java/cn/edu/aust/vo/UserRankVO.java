package cn.edu.aust.vo;

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

  private String email;

  private String avatar;

  private String nickname;

  private String honor;

  private String motto;

}
