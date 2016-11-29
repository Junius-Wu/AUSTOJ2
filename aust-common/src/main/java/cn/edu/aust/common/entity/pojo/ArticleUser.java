package cn.edu.aust.common.entity.pojo;

import cn.edu.aust.common.entity.ArticleBLOBs;
import cn.edu.aust.common.entity.User;

/**
 * @author Niu Li
 * @date 2016/11/28
 */
public class ArticleUser extends ArticleBLOBs {

    private User user;

    /**
     * 是否点赞过
     */
    private Integer isVote = 0;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getIsVote() {
        return isVote;
    }

    public void setIsVote(Integer isVote) {
        this.isVote = isVote;
    }
}
