package cn.edu.aust.query;

import cn.edu.aust.pojo.entity.Article;

/**
 * 文章查询结果包装类
 * @author Niu Li
 * @date 2017/1/30
 */
public class ArticlePC extends Article{
    /**
     * 作者昵称
     */
    private String nickname;

    private Byte isVote;

    public Byte getIsVote() {
        return isVote;
    }

    public void setIsVote(Byte isVote) {
        this.isVote = isVote;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
