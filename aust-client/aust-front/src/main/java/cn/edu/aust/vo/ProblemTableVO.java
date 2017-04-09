package cn.edu.aust.vo;

import com.google.common.collect.Lists;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

import cn.edu.aust.dto.ProblemListDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 表格中题目显示
 * @author Niu Li
 * @since 2017/4/9
 */
@Data
@NoArgsConstructor
@ToString
public class ProblemTableVO {

  private List<ProblemTableDetail> contents;

  private Long total;

  private Integer currentPage;

  public static ProblemTableVO assemble(List<ProblemListDTO> listDTOS,
      Long total, Integer currentPage){
    ProblemTableVO tableVO = new ProblemTableVO();
    List<ProblemTableDetail> result = Lists.newArrayList();
    tableVO.setTotal(total);
    tableVO.setCurrentPage(currentPage);
    tableVO.setContents(result);

    if (CollectionUtils.isEmpty(listDTOS)) {
      return tableVO;
    }
    listDTOS.forEach(x -> {
      ProblemTableDetail temp = new ProblemTableDetail();
      temp.setId(x.getId());
      temp.setDifficulty(x.getDifficulty());
      temp.setKeyword(x.getKeyword());
      temp.setTitle(x.getTitle());
      temp.setAcRate(buildAcRate(x.getSolved(),x.getSubmit()));
      result.add(temp);
    });
    return tableVO;
  }

  private static String buildAcRate(Integer solved,Integer submit){
    if (Objects.isNull(solved) || Objects.isNull(submit)){
      return "0%(0/0)";
    }
    double tempSubmit = submit == 0?1.0:submit;
    double acRate = solved / tempSubmit * 100;
    return String.format("%.2f",acRate)+ "%" + String.format("(%d/%d)",solved,submit);
  }

  @Data
  @NoArgsConstructor
  private static class ProblemTableDetail{
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
