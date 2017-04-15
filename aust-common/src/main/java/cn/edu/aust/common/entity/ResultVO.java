package cn.edu.aust.common.entity;

import java.util.List;

import cn.edu.aust.common.constant.PosCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * 封装返回类,所有的ajax请求都用其包装返回
 *
 * @author Niu Li
 * @date 2017/1/25
 */
@Data
@ToString
public class ResultVO<T> {

  private Integer status;// 是否成功标志

  private T data;// 成功时返回的数据

  private String msg;// 错误信息

  public ResultVO() {
  }

  public ResultVO buildWithMsgAndStatus(PosCode posCode, String msg) {
    this.status = posCode.getStatus();
    this.msg = msg;
    return this;
  }

  public ResultVO buildWithPosCode(PosCode posCode){
    this.status = posCode.getStatus();
    this.msg = posCode.getMsg();
    return this;
  }

  public ResultVO buildOK() {
    this.status = PosCode.OK.getStatus();
    this.msg = PosCode.OK.getMsg();
    return this;
  }
  @SuppressWarnings("unchecked")
  public ResultVO buildOKWithData(T data) {
    this.status = PosCode.OK.getStatus();
    this.msg = PosCode.OK.getMsg();
    this.data = data;
    return this;
  }

  /**
   * 分页数据
   */
  @Data
  @AllArgsConstructor
  public static class paginationData<E>{
    private Long total;
    private Integer pageSize;
    private List<E> contents;
  }

}
