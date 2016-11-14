package cn.edu.aust.common.entity.pojo;

import cn.edu.aust.common.entity.Problem;
import cn.edu.aust.common.entity.ProblemComment;
import cn.edu.aust.common.entity.User;

/**
 * @author Niu Li
 * @date 2016/11/14
 */
public class ProblemCommentUser extends ProblemComment{
    private Problem problem;
    private User user;
    private User friend;

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }
}
