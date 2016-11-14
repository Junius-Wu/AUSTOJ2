package cn.edu.aust.common.entity.pojo;

import cn.edu.aust.common.entity.ProblemWithBLOBs;
import cn.edu.aust.common.entity.User;

/**
 * 包装题目和用户的POJO
 * @author Niu Li
 * @date 2016/11/14
 */
public class ProblemUser extends ProblemWithBLOBs{

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
