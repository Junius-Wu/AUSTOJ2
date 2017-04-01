package cn.edu.aust.common.util;

import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import cn.edu.aust.common.entity.Setting;
import lombok.extern.slf4j.Slf4j;

/**
 * 管理整个后台配置文件
 *
 * @author Niu Li
 * @date 2017/1/25
 */
@Slf4j
public abstract class SystemUtil {
    /**
     * 配置缓存名称
     */
    private static final String SETTING_NAME = "austoj.json";

    /**
     * 得到系统配置
     * @return 结果
     */
    public static Setting getSetting() {
        //从配置文件获取
        try (InputStream in = SystemUtil.class.getClassLoader().getResourceAsStream(SETTING_NAME))
        {
            Setting tempSetting = JSON.parseObject(in, null, Setting.class);
            log.info("get setting :{}",JSON.toJSONString(tempSetting));
            return tempSetting;
        } catch (IOException e) {
            log.error("read setting error", e);
        }
        //最后返回默认配置
        return new Setting();
    }

    /**
     * 设置或刷新系统配置
     * @param setting 设置
     */
    public static void setSetting(Setting setting){
        try {
            File file = new File(SystemUtil.class.getClassLoader().getResource("").getPath()+"austoj.json");
            OutputStream out = new FileOutputStream(file);
            JSON.writeJSONString(out,setting);
        } catch (IOException e) {
            log.error("read setting error",e);
        }
    }

}
