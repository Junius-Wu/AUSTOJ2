package cn.edu.aust;

/**
 * @author Niu Li
 * @date 2016/9/17
 */
public enum  ResultVo {
    OK(0,"success"),CODE_ERROR(1,"验证码错误"),LOGIN_ERROR(2,"用户名或密码错误"),USER_DEFUNCT(3,"用户已被冻结"),USER_LOCKED(4,"用户已被锁定"),
    USER_LOCKED_FOREVER(5,"用户已被锁定,请联系管理员解锁"),USERNAME_EXIST(6,"用户名已存在"),USERNAME_NOALLOW(7,"用户名只能数字,英文,下划线组成3-16字符间"),
    EMAIL_EXIST(8,"邮箱已存在"),REGISTER_ERROR(9,"用户注册信息错误"),REGISTER_ENABLE(10,"网站暂不开放注册"),USERNAME_ENABLE(11,"该用户名已被禁用")
    ,PROBLEM_NOT_EXIST(11,"题目不存在,请问你这是哪找的题目?");

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
