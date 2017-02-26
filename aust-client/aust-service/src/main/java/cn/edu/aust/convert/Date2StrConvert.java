package cn.edu.aust.convert;

import org.modelmapper.AbstractConverter;

import java.util.Date;

import cn.edu.aust.common.util.DateUtil;

/**
 * @author Niu Li
 * @since 2017/2/26
 */
public class Date2StrConvert extends AbstractConverter<Date,String> {

  public static Date2StrConvert date2Str = new Date2StrConvert();

  @Override
  protected String convert(Date date) {
    return DateUtil.format(date,DateUtil.YMDHMS_);
  }
}
