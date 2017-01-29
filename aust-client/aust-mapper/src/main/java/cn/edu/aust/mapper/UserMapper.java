package cn.edu.aust.mapper;

import java.util.List;

import cn.edu.aust.pojo.entity.User;
import tk.mybatis.mapper.common.Mapper;

/**
 * 针对client的mapper
 * @author Niu Li
 * @date 2017/1/22
 */
public interface UserMapper extends Mapper<User> {
    /**
     * 查询首页展示用户
     * @return 结果集
     */
    List<User> queryToIndexShow();

    /**
     * 查询出排名用户
     * @return 结果集
     */
    List<User> queryForRank();
}
