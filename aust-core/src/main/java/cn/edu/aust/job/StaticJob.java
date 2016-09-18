package cn.edu.aust.job;

import com.alibaba.fastjson.JSON;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import cn.edu.aust.entity.User;
import cn.edu.aust.mapper.UserMapper;
import cn.edu.aust.util.FileUtil;
import cn.edu.aust.util.LoggerUtil;

/**
 * @author Niu Li
 * @date 2016/9/11
 */
@Component("staticJob")
public class StaticJob implements InitializingBean {

    private static Logger logger = LoggerFactory.getLogger(StaticJob.class);
    @Resource
    private UserMapper userMapper;

    /**
     * 生成首页展示用户,每24小时更新一次
     */
    @Scheduled(cron = "0 0 0 1/1 * ?")
    public void generateUserToShow() throws IOException {

        List<User> users = userMapper.selecttoShow()
                .stream().limit(6).collect(Collectors.toList());

        String path = String.join(File.separator,System.getProperty("web.root"),"static","json");
        LoggerUtil.debug(logger,()->"展示用户路径:"+path);

        String reslut = JSON.toJSONString(users);

        FileUtil.saveToDisk(path, "user.json", reslut);
        LoggerUtil.info(logger,()->"写入首页展示用户到指定路径中");
    }

    /**
     * 启动立即执行
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        generateUserToShow();
    }
}
