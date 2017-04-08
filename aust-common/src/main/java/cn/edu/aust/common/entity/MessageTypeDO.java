package cn.edu.aust.common.entity;

import lombok.Data;
import lombok.ToString;

/**
 * 消息信息的封装
 * @author Niu Li
 * @since 2017/3/31
 */
@Data
@ToString
public class MessageTypeDO {
  //消息id
  private Integer id;
  //由消息类型决定实体类型id
  private Long entityId;
  //消息类型
  private String type;

}
