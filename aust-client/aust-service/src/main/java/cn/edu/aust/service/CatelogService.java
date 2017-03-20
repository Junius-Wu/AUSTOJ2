package cn.edu.aust.service;

import org.springframework.stereotype.Service;

import java.util.List;

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
public class CatelogService {
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

  /**
   * 查询全部目录
   * @return 全部目录集合
   */
  public List<CatelogDTO> queryAll(){
    List<CatelogDO> list = catelogMapper.selectAll();
    return CatelogAssemble.do2dto(list);
  }
}
