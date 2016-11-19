package cn.edu.aust.util;

import com.alibaba.fastjson.JSON;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import cn.edu.aust.Setting;

/**
 * 得到系统配置
 * @author Niu Li
 * @date 2016/9/17
 */
public class SystemUtil {
    /** CacheManager */
    private static final CacheManager CACHE_MANAGER = CacheManager.create();

    /**
     * 不可实例化
     */
    private SystemUtil(){}

    /**
     * 得到系统设置
     * @return 该设置实例
     */
    public static Setting getSetting(){
        Cache cache = CACHE_MANAGER.getCache(Setting.CACHE_NAME);
        Element elementCache = cache.get(Setting.CACHE_NAME);

        if (elementCache == null){
            Setting setting = null;
            try {
                InputStream in = new ClassPathResource("austoj.json").getInputStream();
                setting = JSON.parseObject(in, null, Setting.class);
            } catch (IOException e) {
                logger.error("获取setting文件出错",e);
            }
            cache.put(new Element(Setting.CACHE_NAME,setting));
            elementCache = cache.get(Setting.CACHE_NAME);
        }
        return (Setting) elementCache.getObjectValue();
    }

    /**
     * 更改系统设置
     * @param setting 新的设置
     */
    public static void setSetting(Setting setting){
        Assert.notNull(setting);
        try {
            File file = new ClassPathResource("austoj.json").getFile();
            OutputStream out = new FileOutputStream(file);
            JSON.writeJSONString(out,setting);
        } catch (IOException e) {
            logger.error("设置setting文件出错",e);
        }
        Cache cache = CACHE_MANAGER.getCache(Setting.CACHE_NAME);
        cache.put(new Element(Setting.CACHE_NAME,setting));
    }

    private static Logger logger = LoggerFactory.getLogger(SystemUtil.class);
}
