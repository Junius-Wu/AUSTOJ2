package cn.edu.aust.job;

import com.alibaba.fastjson.JSON;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import cn.edu.aust.dto.UserRankDTO;
import cn.edu.aust.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户排名定时生成
 * @author Niu Li
 * @date 2017/1/29
 */
@Component
@Slf4j
public class RankJob implements InitializingBean{

    @Resource
    private UserService userService;

    @Scheduled(cron = "${job.rankuser}")
    public void generateUserRank() throws IOException {
        log.info("generateUserRank start ...");
        List<UserRankDTO> users = userService.queryForRank();
        String path = String.join(File.separator,System.getProperty("web.root"),"static","json","rank.json");
        String reslut = JSON.toJSONString(users);
        FileUtils.writeStringToFile(new File(path),reslut,"UTF-8");
        log.info("generateUserRank end , the path :{}"+path);
    }

    /**
     * 注入后执行
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        generateUserRank();
    }
}
