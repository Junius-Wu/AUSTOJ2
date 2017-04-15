package cn.edu.aust.vo;

import com.google.common.collect.Lists;

import org.springframework.util.CollectionUtils;

import java.util.List;

import cn.edu.aust.common.util.ProblemUtil;
import cn.edu.aust.dto.ContestDTO;
import cn.edu.aust.dto.ProblemBasicDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Niu Li
 * @since 2017/4/11
 */
@Data
@NoArgsConstructor
public class ContestDetailVO {
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
  /**
   * 描述信息
   */
  private String description;


  private List<ContestTableDetail> contents;

  public static ContestDetailVO assemble(List<ProblemBasicDTO> listDTOS, ContestDTO contestDTO){
    ContestDetailVO detailVo = new ContestDetailVO();
    List<ContestDetailVO.ContestTableDetail> result = Lists.newArrayList();
    detailVo.setContents(result);

    if (CollectionUtils.isEmpty(listDTOS)) {
      return detailVo;
    }
    listDTOS.forEach(x -> {
      ContestDetailVO.ContestTableDetail temp = new ContestDetailVO.ContestTableDetail();
      temp.setId(x.getId());
      temp.setDifficulty(x.getDifficulty());
      temp.setKeyword(x.getKeyword());
      temp.setTitle(x.getTitle());
      temp.setAcRate(ProblemUtil.buildAcRate(x.getSolved(),x.getSubmit()));
      result.add(temp);
    });
    detailVo.setId(contestDTO.getId());
    detailVo.setCreateUser(contestDTO.getCreateUser());
    detailVo.setDescription(contestDTO.getDescription());
    detailVo.setTitle(contestDTO.getTitle());
    detailVo.setEndTime(contestDTO.getEndTime());
    detailVo.setStartTime(contestDTO.getStartTime());
    return detailVo;
  }


  @Data
  @NoArgsConstructor
  private static class ContestTableDetail{
    private Long id;
    /**
     * 标题
     */
    private String title;

    /**
     * 关键词
     */
    private String keyword;
    /**
     * 难度等级
     */
    private Integer difficulty;

    /**
     * 拼接用于显示的
     */
    private String acRate;
  }
}
