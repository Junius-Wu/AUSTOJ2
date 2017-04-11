package cn.edu.aust.vo;

import java.util.List;

import cn.edu.aust.dto.SolutionDTO;
import lombok.Data;

/**提交列表对应实体
 * @author Niu Li
 * @since 2017/4/11
 */
@Data
public class SubmitTableVO {

  private List<SolutionDTO> contents;

  private Long total;


  public static SubmitTableVO assemble(List<SolutionDTO> contents,Long total){
    SubmitTableVO tableVO = new SubmitTableVO();
    tableVO.setContents(contents);
    tableVO.setTotal(total);
    return tableVO;
  }
}
