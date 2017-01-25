package cn.edu.aust.common.constant;

/**
 * 全局错误码配置
 * @author Niu Li
 * @date 2017/1/25
 */
public enum PosCode {

    OK(0,"SUCCESS"),USERNAME_EXIST(20001,"该账户已存在"),USERNAME_NOALLOW(20002,"用户名不合法"), CODE_ERROR(20003, "验证码错误"),
    NO_REGISTER(50001,"未开放注册"),PASSWORD_NOALLOW(20004,"密码不合法"),NICKNAME_NOALLOW(20005,"昵称不合法"),URL_ERROR(20006,"错误的链接"),
    ALREADY_REGISTER(20007,"未注册或已验证")
    ;


    private Integer status;
    private String msg;

    PosCode(Integer status, String msg) {
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
