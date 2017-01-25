package cn.edu.aust.common.util;

import com.alibaba.fastjson.JSON;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import cn.edu.aust.common.entity.Setting;
import cn.edu.aust.common.service.JedisClient;

/**
 * 管理整个后台配置文件
 *
 * @author Niu Li
 * @date 2017/1/25
 */
public abstract class SystemUtil {
    /**
     * 配置缓存名称
     */
    public static final String SETTING_CACHE = "setting";

    private static Logger logger = LoggerFactory.getLogger(SystemUtil.class);

    /**
     * 得到系统配置 缓存>配置
     * @param jedisClient redis缓存工具
     * @return 结果
     */
    public static Setting getSetting(JedisClient jedisClient) {
        //从缓存中获取
        String setting = jedisClient.get(SETTING_CACHE);
        if (StringUtils.isNoneEmpty(setting)) {
            logger.info("从缓存中获取的配置文件:{}", setting);
            return JSON.parseObject(setting, Setting.class);
        }
        //缓存不存在则从配置文件获取
        try (InputStream in = SystemUtil.class.getClassLoader().getResourceAsStream("austoj.json"))
        {
            Setting tempSetting = JSON.parseObject(in, null, Setting.class);
            logger.info("从配置文件中获取的配置:{}",JSON.toJSONString(tempSetting));
            return tempSetting;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("获取setting文件出错", e);
        }
        //最后返回默认配置
        return new Setting();
    }

    /**
     * 设置或刷新系统配置
     * @param setting 设置
     * @param jedisClient 缓存工具
     */
    public static void setSetting(Setting setting,JedisClient jedisClient){
        try {
            File file = new File(SystemUtil.class.getClassLoader().getResource("").getPath()+"austoj.json");
            OutputStream out = new FileOutputStream(file);
            JSON.writeJSONString(out,setting);
        } catch (IOException e) {
            logger.error("设置setting文件出错",e);
        }
        jedisClient.set(SETTING_CACHE,JSON.toJSONString(setting));
    }

//    测试获取路径
    public static void main(String[] args) {
        System.out.println(SystemUtil.class.getClassLoader().getResource("").getPath()+"austoj.json");
    }
}
