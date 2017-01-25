package cn.edu.aust.common.entity;

/**
 * 封装返回类,所有的ajax请求都用其包装返回
 * @author Niu Li
 * @date 2017/1/25
 */
public class Result<T> {

    private boolean status;// 是否成功标志

    private T data;// 成功时返回的数据

    private String msg;// 错误信息

    public Result() {
    }

    // 成功时的构造器
    public Result(boolean status, T data) {
        this.status = status;
        this.data = data;
    }

    // 错误时的构造器
    public Result(boolean status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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
