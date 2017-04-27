package cn.edu.aust.utilconvert;

import org.modelmapper.AbstractConverter;

import cn.edu.aust.common.constant.JudgeCode;

/**
 * 判题错误码转换
 * @author Niu Li
 * @since 2017/3/18
 */
public class JudgeCodeConvent extends AbstractConverter<Integer,JudgeCode> {

  public static final JudgeCodeConvent judgeCodeConvent = new JudgeCodeConvent();

  @Override
  protected JudgeCode convert(Integer integer) {
    return JudgeCode.statusOf(integer);
  }
}
