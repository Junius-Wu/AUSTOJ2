package cn.edu.aust.common.util;

import java.util.Objects;

/**
 * @author Niu Li
 * @since 2017/4/11
 */
public class ProblemUtil {

  /**
   * 给出ac数和submit数计算出显示串
   * @param solved ac数
   * @param submit submit数量
   * @return 结果串
   */
  public static String buildAcRate(Integer solved,Integer submit){
    if (Objects.isNull(solved) || Objects.isNull(submit)){
      return "0%(0/0)";
    }
    double tempSubmit = submit == 0?1.0:submit;
    double acRate = solved / tempSubmit * 100;
    return String.format("%.2f",acRate)+ "%" + String.format("(%d/%d)",solved,submit);
  }
}
