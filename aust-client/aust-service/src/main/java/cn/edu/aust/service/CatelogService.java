package cn.edu.aust.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import cn.edu.aust.assemble.CatelogAssemble;
import cn.edu.aust.dto.CatelogDTO;
import cn.edu.aust.mapper.CatelogMapper;
import cn.edu.aust.pojo.entity.CatelogDO;

/**
 * @author Niu Li
 * @date 2017/1/29
 */
@Service
public class CatelogService extends BaseService<CatelogDO> {
  @Resource
  private CatelogMapper catelogMapper;

  /**
   * 根据主键查询一个目录
   * @param id 主键
   * @return 该目录
   */
  public CatelogDTO findById(Integer id){
    CatelogDO catelogDO = catelogMapper.selectByPrimaryKey(id);
    return CatelogAssemble.do2dto(catelogDO);
  }
}
