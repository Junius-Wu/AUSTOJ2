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
public class MessageType {
  //消息id
  private Integer id;
  //目标id
  private Long objectId;
  //主体id
  private Long subjectId;
  //消息类型
  private String type;

}
