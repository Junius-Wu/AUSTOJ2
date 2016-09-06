package cn.edu.aust.mapper;

import cn.edu.aust.entity.Mail;

public interface MailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Mail record);

    int insertSelective(Mail record);

    Mail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Mail record);

    int updateByPrimaryKeyWithBLOBs(Mail record);

    int updateByPrimaryKey(Mail record);
}