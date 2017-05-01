package cn.edu.aust.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

import cn.edu.aust.pojo.entity.UserDO;
import tk.mybatis.mapper.common.Mapper;

/**
 * 针对client的mapper
 * @author Niu Li
 * @since  2017/1/22
 */
public interface UserMapper extends Mapper<UserDO> {

    UserDO findByEmail(String email);

    /**
     * 查询首页展示用户
     * @return 结果集
     */
    List<UserDO> queryToShow();

    /**
     * 查询出排名用户
     * @return 结果集
     */
    List<UserDO> queryForRank();

    /**
     * 根据id集合查询用户
     * @param ids id集合
     * @return 用户
     */
    List<UserDO> queryBaseByIds(@Param("ids") Collection<Long> ids);
}
