package cn.edu.aust.mapper;

import java.util.List;

import cn.edu.aust.pojo.entity.TagsDO;
import tk.mybatis.mapper.common.Mapper;

public interface TagsMapper extends Mapper<TagsDO> {
    /**
     * 按照点击数排序查找
     * @return 结果集
     */
    List<TagsDO> queryList();
}