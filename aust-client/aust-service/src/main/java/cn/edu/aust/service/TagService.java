package cn.edu.aust.service;

import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import cn.edu.aust.mapper.TagsMapper;
import cn.edu.aust.pojo.entity.Tags;

/**
 * 标签
 * @author Niu Li
 * @date 2017/1/29
 */
@Service
public class TagService extends BaseService<Tags>{
    @Autowired
    private TagsMapper tagsMapper;

    /**
     * 按照count排序查找
     * @param limit 数量
     * @return 结果集
     */
    public List<Tags> queryList(Integer limit){
        PageHelper.startPage(0,limit);
        return tagsMapper.queryList();
    }
}
