package cn.edu.aust.convert;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import cn.edu.aust.dto.ArticleAsideDTO;
import cn.edu.aust.dto.ArticleDTO;
import cn.edu.aust.dto.ArticleListDTO;
import cn.edu.aust.pojo.entity.ArticleDO;
import cn.edu.aust.query.ArticlePK;

/**
 * @author Niu Li
 * @since 2017/3/20
 */
public class ArticleConvert {
  private static final ModelMapper modelMapper = new ModelMapper();

  /**
   * 文章包装了转dto
   *
   * @param articlePK 包装类
   * @return dot模型
   */
  public static ArticleDTO pk2dto(ArticlePK articlePK) {
    if (Objects.isNull(articlePK)) {
      return null;
    }
    return modelMapper.map(articlePK, ArticleDTO.class);
  }

  /**
   * do转侧边栏实体
   * @param articleDOS do模型
   * @return 侧边栏模型集合
   */
  public static List<ArticleAsideDTO> do2AsideDto(List<ArticleDO> articleDOS){
    if (CollectionUtils.isEmpty(articleDOS)){
      return Collections.emptyList();
    }
    return modelMapper.map(articleDOS, new TypeToken<List<ArticleAsideDTO>>() {}.getType());
  }

  /**
   * 包装类转侧边栏实体
   * @param articlePKS 包装类模型
   * @return 侧边栏模型集合
   */
  public static List<ArticleListDTO> pk2ListDto(List<ArticlePK> articlePKS){
    if (CollectionUtils.isEmpty(articlePKS)){
      return Collections.emptyList();
    }
    return modelMapper.map(articlePKS, new TypeToken<List<ArticleListDTO>>() {}.getType());
  }

  /**
   * do转dto
   * @param articleDO do实体
   * @return dto实体
   */
  public static ArticleDTO do2dto(ArticleDO articleDO){
    if (Objects.isNull(articleDO)){
      return null;
    }
    return modelMapper.map(articleDO,ArticleDTO.class);
  }
}
