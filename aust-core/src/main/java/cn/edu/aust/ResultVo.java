package cn.edu.aust;

/**
 * @author Niu Li
 * @date 2016/9/17
 */
public enum  ResultVo {
    OK(0,"success"),CODE_ERROR(1,"验证码错误"),LOGIN_ERROR(2,"用户名或密码错误"),USER_DEFUNCT(3,"用户已被冻结"),USER_LOCKED(4,"用户已被锁定"),
    USER_LOCKED_FOREVER(5,"用户已被锁定,请联系管理员解锁");

    private Integer status;
    private String msg;

    ResultVo(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
