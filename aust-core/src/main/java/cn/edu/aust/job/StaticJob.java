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

import cn.edu.aust.common.entity.User;
import cn.edu.aust.service.UserService;
import cn.edu.aust.util.FileUtil;
import cn.edu.aust.util.LoggerUtil;

/**
 * @author Niu Li
 * @date 2016/9/11
 */
@Component("staticJob")
public class StaticJob implements InitializingBean {

    private static Logger logger = LoggerFactory.getLogger(StaticJob.class);
    @Resource(name = "userServiceImpl")
    private UserService userService;

    /**
     * 生成首页展示用户,每24小时更新一次
     */
    @Scheduled(cron = "0 0 0 1/1 * ?")
    public void generateUserToShow() throws IOException {

        List<User> users = userService.selectToShow()
                .stream().limit(6).collect(Collectors.toList());

        String path = String.join(File.separator,System.getProperty("web.root"),"static","json");

        String reslut = JSON.toJSONString(users);

        FileUtil.saveToDisk(path, "user.json", reslut);
        LoggerUtil.info(logger,()->"写入首页展示用户到指定路径中");
    }

    /**
     * 生成用户排名,每2小时更新
     */
    @Scheduled(cron = "0 0 0/2 * * ?")
    public void generateUserRank() throws IOException {
        List<User> users = userService.selectRanks();
//        JSONObject userObj = new JSONObject();
//        userObj.put("total",users.size());
//        userObj.put("rows",users);

        String path = String.join(File.separator,System.getProperty("web.root"),"static","json");

        String reslut = JSON.toJSONString(users);

        FileUtil.saveToDisk(path, "rank.json", reslut);
        LoggerUtil.info(logger,()->"写入排名到指定路径中");
    }

    /**
     * 启动立即执行
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        generateUserToShow();
        generateUserRank();
    }
}
