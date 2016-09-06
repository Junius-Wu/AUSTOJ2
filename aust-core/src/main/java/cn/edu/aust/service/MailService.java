package cn.edu.aust.service;

import cn.edu.aust.entity.Mail;

/**
 * @author Niu Li
 * @date 2016/9/6
 */
public interface MailService {

    int deleteByPrimaryKey(Integer id);

    int insert(Mail record);

    int insertSelective(Mail record);

    Mail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Mail record);

    int updateByPrimaryKeyWithBLOBs(Mail record);

    int updateByPrimaryKey(Mail record);
}
