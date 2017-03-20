package cn.edu.aust.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 用户排名展示类
 * @author Niu Li
 * @date 2017/1/29
 */
@Data
@NoArgsConstructor
@ToString
public class UserRankDTO {

    private Long id;

    private String avatar;

    private String nickname;

    private Integer point;

    private Integer submit;

    private Integer solved;

    private String blog;
}
