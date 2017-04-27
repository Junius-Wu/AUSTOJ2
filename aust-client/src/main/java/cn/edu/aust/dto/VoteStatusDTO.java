package cn.edu.aust.dto;

import lombok.Data;

/**
 * 保存点赞状态
 * @author Niu Li
 * @since 2017/4/14
 */
@Data
public class VoteStatusDTO {
  private Integer status;
  private Integer count;
}
