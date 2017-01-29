package cn.edu.aust.common.entity;

import java.io.Serializable;

/**
 * 该项目后台设置类
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
    /**
     * 是否启用注册
     */
    private boolean isRegisterEnabled;
    /**
     * 禁止注册的用户名
     */
    private String disabledUsernames;
    /**
     * 侧边栏标签数
     */
    private int aside_tags;
    /**
     * 侧边栏文章数
     */
    private int aside_articles;
    /**
     * isCommentEnabled : true
     * isCommentChecked : false
     */
    /**
     * 是否开启评论
     */
    private boolean isCommentEnabled;
    /**
     * 是否开启评论审核
     */
    private boolean isCommentChecked;
    /**
     * 通知数量
     */

    private int notify_count;


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

    public boolean isIsRegisterEnabled() {
        return isRegisterEnabled;
    }

    public void setIsRegisterEnabled(boolean isRegisterEnabled) {
        this.isRegisterEnabled = isRegisterEnabled;
    }

    public String getDisabledUsernames() {
        return disabledUsernames;
    }

    public void setDisabledUsernames(String disabledUsernames) {
        this.disabledUsernames = disabledUsernames;
    }

    public int getAside_tags() {
        return aside_tags;
    }

    public void setAside_tags(int aside_tags) {
        this.aside_tags = aside_tags;
    }

    public int getAside_articles() {
        return aside_articles;
    }

    public void setAside_articles(int aside_articles) {
        this.aside_articles = aside_articles;
    }

    public boolean isIsCommentEnabled() {
        return isCommentEnabled;
    }

    public void setIsCommentEnabled(boolean isCommentEnabled) {
        this.isCommentEnabled = isCommentEnabled;
    }

    public boolean isIsCommentChecked() {
        return isCommentChecked;
    }

    public void setIsCommentChecked(boolean isCommentChecked) {
        this.isCommentChecked = isCommentChecked;
    }

    public int getNotify_count() {
        return notify_count;
    }

    public void setNotify_count(int notify_count) {
        this.notify_count = notify_count;
    }
}
