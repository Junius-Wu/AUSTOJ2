package cn.edu.aust.convert;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import cn.edu.aust.dto.ArticleAsideDTO;
import cn.edu.aust.dto.ArticleDTO;
import cn.edu.aust.dto.ArticleBasicDTO;
import cn.edu.aust.pojo.entity.ArticleDO;
import cn.edu.aust.query.ArticlePO;

/**
 * @author Niu Li
 * @since 2017/3/20
 */
public class ArticleConvert {

  private static final ModelMapper modelMapper = new ModelMapper();

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

  /**
   * 包装类转侧边栏实体
   * @param articlePOS 包装类模型
   * @return 侧边栏模型集合
   */
  public static List<ArticleBasicDTO> articlePo2basicDto(List<ArticlePO> articlePOS){
    if (CollectionUtils.isEmpty(articlePOS)){
      return Collections.emptyList();
    }
    return modelMapper.map(articlePOS, new TypeToken<List<ArticleBasicDTO>>() {}.getType());
  }

  /**
   * 文章包装了转dto
   *
   * @param articlePO 包装类
   * @return dot模型
   */
  public static ArticleDTO articlePo2dto(ArticlePO articlePO) {
    if (Objects.isNull(articlePO)) {
      return null;
    }
    return modelMapper.map(articlePO, ArticleDTO.class);
  }

}
