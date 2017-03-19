package cn.edu.aust.common.entity;

import cn.edu.aust.common.constant.PosCode;
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

  // 成功时的构造器
  public ResultVO(PosCode posCode, T data) {
    this.status = posCode.getStatus();
    this.msg = posCode.getMsg();
    this.data = data;
  }

  // 错误时的构造器
  public ResultVO(PosCode posCode) {
    this.status = posCode.getStatus();
    this.msg = posCode.getMsg();
  }

  public ResultVO(int status, String msg) {
    this.status = status;
    this.msg = msg;
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
  public ResultVO buildOKWithData(T data) {
    this.status = PosCode.OK.getStatus();
    this.msg = PosCode.OK.getMsg();
    this.data = data;
    return this;
  }

}
