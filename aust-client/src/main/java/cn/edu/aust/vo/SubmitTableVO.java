package cn.edu.aust.vo;

import com.google.common.collect.Lists;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

import cn.edu.aust.common.util.DateUtil;
import cn.edu.aust.dto.SolutionDTO;
import lombok.Data;

/**提交列表对应实体
 * @author Niu Li
 * @since 2017/4/11
 */
@Data
public class SubmitTableVO {

  private Long id;
  private Long problemId;
  private String problemTitle;
  private Long userId;
  private Double memory;
  private Integer time;
  private Double codeLength;
  private String language;
  private Integer contestId;
  private String verdict;
  private Integer verdictCode;
  private Integer testcase;
  private String createdate;


  public static List<SubmitTableVO> assemble(List<SolutionDTO> contents){
    List<SubmitTableVO> tableVOS = Lists.newArrayList();
    if (CollectionUtils.isEmpty(contents)) {
      return tableVOS;
    }
    contents.forEach(x -> tableVOS.add(assembler(x)));
    return tableVOS;
  }


  public static SubmitTableVO assembler(SolutionDTO solutionDTO) {
    SubmitTableVO tableVO = new SubmitTableVO();
    tableVO.setId(solutionDTO.getId());
    tableVO.setProblemId(solutionDTO.getProblemId());
    tableVO.setProblemTitle(solutionDTO.getProblemTitle());
    tableVO.setUserId(solutionDTO.getUserId());
    tableVO.setMemory(Optional.ofNullable(solutionDTO.getMemory()).orElse(0) /1000.0);
    tableVO.setTime(solutionDTO.getTime());
    tableVO.setCodeLength(solutionDTO.getCodeLength());
    tableVO.setLanguage(solutionDTO.getLanguage());
    tableVO.setContestId(solutionDTO.getContestId());
    tableVO.setVerdict(solutionDTO.getVerdict().getMsg());
    tableVO.setVerdictCode(solutionDTO.getVerdict().getStatus());
    tableVO.setTestcase(solutionDTO.getTestcase());
    tableVO.setCreatedate(DateUtil.format(solutionDTO.getCreatedate(),DateUtil.YMDHMS_));
    return tableVO;
  }
}
