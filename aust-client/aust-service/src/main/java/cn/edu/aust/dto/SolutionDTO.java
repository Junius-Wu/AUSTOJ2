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
  private Long problemId;
  private String problemTitle;
  private Long userId;
  private Integer memory;
  private Integer time;
  private Double codeLength;
  private String language;
  private Integer contestId;
  private String verdict;
  private Integer testcase;

  @JSONField(name = "createdate",format = "yyyy-MM-dd HH:mm:ss")
  private Date createdate;

}
