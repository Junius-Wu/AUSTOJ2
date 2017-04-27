package cn.edu.aust.dto;

import java.util.Date;

import cn.edu.aust.common.constant.JudgeCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Niu Li
 * @since 2017/3/18
 */
@Data
@NoArgsConstructor
@ToString
public class SolutionDTO {
  private Long id;
  private Long problemId;
  private String problemTitle;
  private Long userId;
  private Integer memory;
  private Integer time;
  private Double codeLength;
  private String language;
  private Integer contestId;
  private JudgeCode verdict;
  private Integer testcase;

  private Date createdate;

}
