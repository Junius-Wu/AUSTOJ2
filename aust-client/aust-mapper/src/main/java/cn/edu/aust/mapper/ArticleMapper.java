package cn.edu.aust.mapper;

import java.util.List;

import cn.edu.aust.pojo.entity.ArticleDO;
import cn.edu.aust.query.ArticleDOPC;
import cn.edu.aust.query.ArticleQM;
import tk.mybatis.mapper.common.Mapper;

public interface ArticleMapper extends Mapper<ArticleDO> {
    /**
     * 侧边栏展示专用,含有极少的信息
     * @return 查询列表,分页从外面做
     */
    List<ArticleDO> queryForAside();

    /**
     * 查询文章页面显示的列表
     * @return 结果集
     */
    List<ArticleDOPC> queryList(ArticleQM articleQM);

    /**
     * 根据id查询一个文章包装类型
     * @param id 文章id
     * @return 包装结果
     */
    ArticleDOPC queryDetail(Long id);
}