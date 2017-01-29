package cn.edu.aust.job;

import com.alibaba.fastjson.JSON;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cn.edu.aust.dto.UserRankDTO;
import cn.edu.aust.service.UserService;

/**
 * 用户排名定时生成
 * @author Niu Li
 * @date 2017/1/29
 */
@Component
public class RankJob implements InitializingBean{

    private static Logger logger = LoggerFactory.getLogger(RankJob.class);
    @Autowired
    private UserService userService;

    @Scheduled(cron = "${job.rankuser}")
    public void generateUserRank() throws IOException {
        List<UserRankDTO> users = userService.queryForRank();
        String path = String.join(File.separator,System.getProperty("web.root"),"static","json","rank.json");
        String reslut = JSON.toJSONString(users);
        FileUtils.writeStringToFile(new File(path),reslut,"UTF-8");
        logger.info("写入用户排名到:{}"+path);
    }

    /**
     * 注入后执行
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        generateUserRank();
    }
}
