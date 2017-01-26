package cn.edu.aust.common.entity;

import cn.edu.aust.common.constant.PosCode;

/**
 * 封装返回类,所有的ajax请求都用其包装返回
 * @author Niu Li
 * @date 2017/1/25
 */
public class Result<T> {

    private Integer status;// 是否成功标志

    private T data;// 成功时返回的数据

    private String msg;// 错误信息

    public Result() {
    }

    // 成功时的构造器
    public Result(PosCode posCode,T data) {
        this.status = posCode.getStatus();
        this.msg = posCode.getMsg();
        this.data = data;
    }

    // 错误时的构造器
    public Result(PosCode posCode) {
        this.status = posCode.getStatus();
        this.msg = posCode.getMsg();
    }

    public Result(int status,String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "JsonResult [status=" + status + ", data=" + data + ", msg=" + msg + "]";
    }
}
