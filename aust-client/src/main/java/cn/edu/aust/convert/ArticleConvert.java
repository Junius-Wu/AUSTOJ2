package cn.edu.aust.convert;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import cn.edu.aust.dto.BaseArticleDTO;
import cn.edu.aust.pojo.entity.ArticleDO;

/**
 * @author Niu Li
 * @since 2017/3/20
 */
public class ArticleConvert {

  private static final ModelMapper modelMapper = new ModelMapper();

  /**
   * do转dto
   * @param articleDO do实体
   * @return dto实体
   */
  public static BaseArticleDTO do2dto(ArticleDO articleDO){
    if (Objects.isNull(articleDO)){
      return null;
    }
    return modelMapper.map(articleDO,BaseArticleDTO.class);
  }

  /**
   * 包装类转侧边栏实体
   * @param ArticleDOS 包装类模型
   * @return 侧边栏模型集合
   */
  public static List<BaseArticleDTO> do2dto(List<ArticleDO> ArticleDOS){
    if (CollectionUtils.isEmpty(ArticleDOS)){
      return Collections.emptyList();
    }
    return modelMapper.map(ArticleDOS, new TypeToken<List<BaseArticleDTO>>() {}.getType());
  }

}
