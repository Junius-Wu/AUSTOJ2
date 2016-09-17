package cn.edu.aust;

import java.io.Serializable;

/**
 * @author Niu Li
 * @date 2016/9/17
 */
public class Setting implements Serializable{

    public static final String CACHE_NAME = "setting";


    /**
     * isEmailLogin : true
     * accountLockCount : 5
     * accountLockTime : 5000
     */
    /**
     * 是否采用邮件登录
     */
    private boolean isEmailLogin;
    /**
     * 密码错误该次数后锁定账户
     */
    private int accountLockCount;
    /**
     * 锁定时间,单位分钟
     */
    private int accountLockTime;
    /**
     * cookies位置
     */
    private String cookiePath;
    /**
     * cookies作用域
     */
    private String cookieDomain;

    public boolean isIsEmailLogin() {
        return isEmailLogin;
    }

    public void setIsEmailLogin(boolean isEmailLogin) {
        this.isEmailLogin = isEmailLogin;
    }

    public int getAccountLockCount() {
        return accountLockCount;
    }

    public void setAccountLockCount(int accountLockCount) {
        this.accountLockCount = accountLockCount;
    }

    public int getAccountLockTime() {
        return accountLockTime;
    }

    public void setAccountLockTime(int accountLockTime) {
        this.accountLockTime = accountLockTime;
    }

    public String getCookiePath() {
        return cookiePath;
    }

    public void setCookiePath(String cookiePath) {
        this.cookiePath = cookiePath;
    }

    public String getCookieDomain() {
        return cookieDomain;
    }

    public void setCookieDomain(String cookieDomain) {
        this.cookieDomain = cookieDomain;
    }
}
