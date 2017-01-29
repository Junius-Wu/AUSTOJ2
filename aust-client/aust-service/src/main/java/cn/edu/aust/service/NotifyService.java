package cn.edu.aust.service;

import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import cn.edu.aust.common.util.DateUtil;
import cn.edu.aust.mapper.NotifyMapper;
import cn.edu.aust.pojo.entity.Notify;

/**
 * 网站通知公告服务
 * @author Niu Li
 * @date 2017/1/29
 */
@Service
public class NotifyService extends BaseService<Notify> {
    @Autowired
    private NotifyMapper notifyMapper;

    /**
     * 查询截止到当前可用的通知
     * @param limit 查询数量
     * @return 结果集
     */
    public List<Notify> queryListNow(int limit){
        PageHelper.startPage(0,limit,false);
        return notifyMapper.queryListNow(DateUtil.format(new Date(),DateUtil.YMDHMS_));
    }
}
