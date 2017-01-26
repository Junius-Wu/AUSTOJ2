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
    List<User> queryToIndexShow();
}
