package cn.edu.aust.service;

import com.github.pagehelper.PageHelper;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.edu.aust.common.util.DateUtil;
import cn.edu.aust.convert.NotifyConvert;
import cn.edu.aust.dto.NotifyDTO;
import cn.edu.aust.mapper.NotifyMapper;
import cn.edu.aust.pojo.entity.NotifyDO;

/**
 * 网站通知公告服务
 * @author Niu Li
 * @date 2017/1/29
 */
@Service
public class NotifyService {
    @Resource
    private NotifyMapper notifyMapper;

    /**
     * 查询截止到当前可用的通知
     * @param limit 查询数量
     * @return 结果集
     */
    public List<NotifyDTO> queryListNow(int limit){
        PageHelper.startPage(0,limit,false);
        List<NotifyDO> notifyDOS = notifyMapper.queryAllOrderTime(DateUtil.format(new Date(), DateUtil.YMDHMS_));
        return NotifyConvert.notify2DTO(notifyDOS);
    }
}
