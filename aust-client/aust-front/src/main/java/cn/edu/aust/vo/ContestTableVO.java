package cn.edu.aust.vo;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.edu.aust.dto.ContestDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Niu Li
 * @since 2017/4/12
 */
@Data
@NoArgsConstructor
public class ContestTableVO {

  /**
   * 过期的数据
   */
  private List<ContestTableDetail> exprieContest;
  /**
   * 未过期的数据,未过期只显示5条
   */
  private List<ContestTableDetail> noExprieContest;


  public static ContestTableVO assemble(Map<String, List<ContestDTO>> contests){
    ContestTableVO tableVO = new ContestTableVO();
    List<ContestDTO> exprieContestDTOS = contests.get("exprieContest");
    List<ContestDTO> noExprieContestDTOS = contests.get("noExprieContest");
    if (!CollectionUtils.isEmpty(exprieContestDTOS)) {
      tableVO.setExprieContest(exprieContestDTOS.stream()
          .map(ContestTableVO::assemble).collect(Collectors.toList()));
    }
    if (!CollectionUtils.isEmpty(noExprieContestDTOS)) {
      tableVO.setExprieContest(noExprieContestDTOS.stream()
          .map(ContestTableVO::assemble).collect(Collectors.toList()));
    }
    return tableVO;
  }


  private static ContestTableDetail assemble(ContestDTO contestDTO){
    ContestTableDetail tableDetail = new ContestTableDetail();
    tableDetail.setId(contestDTO.getId());
    tableDetail.setTitle(contestDTO.getTitle());
    tableDetail.setStartTime(contestDTO.getStartTime());
    tableDetail.setEndTime(contestDTO.getEndTime());
    tableDetail.setCreateUser(contestDTO.getCreateUser());
    tableDetail.setTypeName(contestDTO.getTypeName());
    return tableDetail;
  }

  @Data
  @NoArgsConstructor
  private static class ContestTableDetail{
    /**
     * 主键
     */
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 创建人
     */
    private String createUser;

    private String typeName;
  }
}
