package cn.edu.aust.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Niu Li
 * @date 2017/1/25
 */
@Data
@NoArgsConstructor
@ToString
public class UserDTO {
    private Long id;
    private String email;
    private String avatar;
    private String nickname;
    private String honor;
    private String intro;
    private String blog;
    private Integer submit;
    private Integer solved;
    private String language;
    private Integer status;

}
