package cn.edu.aust.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 展示排名类的DTO
 * @author Niu Li
 * @since 2017/2/26
 */
@Data
@ToString
public class ContestDTO {
  /**
   * 主键
   */
  private Integer id;
  /**
   * 标题
   */
  private String title;
  /**
   * 开始时间
   */
  private String startTime;
  /**
   * 结束时间
   */
  private String endTime;
  /**
   * 1校赛,2公开赛
   */
  private String typeName;
  /**
   * 创建人
   */
  private String createUser;
  /**
   * 描述信息
   */
  private String description;
}
