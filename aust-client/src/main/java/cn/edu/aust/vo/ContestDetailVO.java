package cn.edu.aust.vo;

import com.google.common.collect.Lists;

import org.springframework.util.CollectionUtils;

import java.util.List;

import cn.edu.aust.common.util.DateUtil;
import cn.edu.aust.common.util.ProblemUtil;
import cn.edu.aust.dto.ContestDTO;
import cn.edu.aust.pojo.entity.ContestProblemDO;
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

  public static ContestDetailVO assemble(List<ContestProblemDO> listDTOS, ContestDTO contestDTO){
    ContestDetailVO detailVo = new ContestDetailVO();
    List<ContestDetailVO.ContestTableDetail> result = Lists.newArrayList();
    detailVo.setContents(result);

    if (CollectionUtils.isEmpty(listDTOS)) {
      return detailVo;
    }
    listDTOS.forEach(x -> {
      ContestDetailVO.ContestTableDetail temp = new ContestDetailVO.ContestTableDetail();
      temp.setNum(x.getNum());
      temp.setProblemId(x.getProblemId());
      temp.setProblemTitle(x.getProblemTitle());
      temp.setAcRate(ProblemUtil.buildAcRate(x.getSolved(),x.getSubmit()));
      result.add(temp);
    });
    detailVo.setId(contestDTO.getId());
    detailVo.setCreateUser(contestDTO.getCreateUser());
    detailVo.setDescription(contestDTO.getDescription());
    detailVo.setTitle(contestDTO.getTitle());
    detailVo.setEndTime(DateUtil.format(contestDTO.getEndTime(),DateUtil.YMDHMS_));
    detailVo.setStartTime(DateUtil.format(contestDTO.getStartTime(),DateUtil.YMDHMS_));
    return detailVo;
  }


  @Data
  @NoArgsConstructor
  private static class ContestTableDetail{
    /**
     * 题号
     */
    private String num;
    /**
     * 题目id
     */
    private Long problemId;
    /**
     * 标题
     */
    private String problemTitle;
    /**
     * 拼接用于显示的
     */
    private String acRate;
  }
}
