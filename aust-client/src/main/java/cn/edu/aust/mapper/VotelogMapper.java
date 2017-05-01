package cn.edu.aust.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Set;

import cn.edu.aust.pojo.entity.VotelogDO;
import tk.mybatis.mapper.common.Mapper;

public interface VotelogMapper extends Mapper<VotelogDO> {

  Set<Long> queryUserLikeArticle(@Param("ids") Collection<Long> ids,
      @Param("userId") Long userId);
}