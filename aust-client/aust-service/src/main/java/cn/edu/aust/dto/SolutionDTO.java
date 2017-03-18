package cn.edu.aust.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

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

  @JSONField(name = "problem_id")
  private Long problemId;
  @JSONField(name = "problem_title")
  private String problemTitle;
  @JSONField(name = "user_id")
  private Long userId;

  private Integer memory;

  private Integer time;
  @JSONField(name = "code_length")
  private Double codeLength;

  private String language;
  @JSONField(name = "contest_id")
  private Integer contestId;

  private String verdict;

  private Integer testcase;

  @JSONField(name = "createdate",format = "yyyy-MM-dd HH:mm:ss")
  private Date createdate;

}
