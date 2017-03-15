package cn.edu.aust.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

/**
 * todo 需要替换掉,service不适合提供这些增删改查基本方法,service应该是针对业务来做封装
 * 利用通用mapper的泛型注入实现通用service模板
 * @author Niu Li
 * @date 2017/1/25
 */
public class BaseService <T> {

    @Autowired
    private Mapper<T> mapper;

    /**
     * 根据id查询
     */
    public T queryById(Object id){
        return this.mapper.selectByPrimaryKey(id);
    }
    /**
     * 根据条件查询一条数据
     */
    public T queryOne(T example){
        return this.mapper.selectOne(example);
    }

    /**
     * 查询所有数据
     */
    public List<T> queryAll(){
        return this.mapper.select(null);
    }

    /**
     * 根据条件查询数据列表
     */
    public List<T> queryListByWhere(T example){
        return this.mapper.select(example);
    }

    /**
     * 分页查询数据列表
     * @param example 查询条件
     * @param page 页数
     * @param rows 页面大小
     * @return 封装的结果集
     */
    public PageInfo<T> queryPageListByWhere(T example, Integer page, Integer rows){

        //设置分页参数
        PageHelper.startPage(page,rows);
        //执行查询
        List<T> list = this.mapper.select(example);
        return new PageInfo<T>(list);
    }
    /**
     * 新增数据，注意设置数据的创建和更新时间
     * 返回成功的条数
     */
    @Transactional
    public Integer save(T t){
        return this.mapper.insertSelective(t);

    }
    /**
     * 更新数据，设置数据的更新时间
     * 返回成功的条数
     */
    @Transactional
    public Integer update(T t){
        return this.mapper.updateByPrimaryKey(t);
    }

    /**
     * 更新数据，设置数据的更新时间（更新部分数据）
     * 返回成功的条数
     */
    @Transactional
    public Integer updateSelective(T t){
        return this.mapper.updateByPrimaryKeySelective(t);
    }
    /**
     * 根据id删除数据
     */
    @Transactional
    public Integer deleteById(Long id){
        return this.mapper.deleteByPrimaryKey(id);
    }
    /**
     * 批量删除数据
     * @param clazz 要删除的实体类
     * @param property 指定条件字段
     * @param list 符合的数据
     * @return 删除后剩余结果数
     */
    @Transactional
    public Integer deleteByIds(Class<T> clazz,String property,List<Object> list){
        Example example=new Example(clazz);
        example.createCriteria().andIn(property,list);
        return this.mapper.deleteByExample(example);
    }

    /**
     * 根据条件删除数据
     */
    @Transactional
    public Integer deleteByWhere(T example){
        return this.mapper.delete(example);
    }


}
