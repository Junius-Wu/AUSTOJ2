package cn.edu.aust.dto;

import java.util.Date;

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
  private Long id;
  /**
   * 标题
   */
  private String title;
  /**
   * 开始时间
   */
  private Date startTime;
  /**
   * 结束时间
   */
  private Date endTime;
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
