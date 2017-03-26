package cn.edu.aust.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import cn.edu.aust.common.service.JedisClient;

/**
 * 邮件服务
 * @author Niu Li
 * @date 2017/1/25
 */
@Service
public class MailService {

    @Resource
    private JavaMailSenderImpl javaMailSender;
    @Resource
    private ThreadPoolTaskExecutor taskExecutor;
    @Value("${mail.urlprefix}")
    private String mailPrefix;//邮件验证连接前缀

    private static Logger logger = LoggerFactory.getLogger(MailService.class);
    /**
     * 注册后发送验证邮件
     * @param email 邮箱
     * @param jedisClient 缓存
     */
    public void sendRegister(String email, JedisClient jedisClient){
        String token = DigestUtils.sha256Hex(email+System.currentTimeMillis());
        // 构建简单邮件对象
        SimpleMailMessage smm = new SimpleMailMessage();
        // 设定邮件参数
        smm.setFrom(javaMailSender.getUsername());
        smm.setTo(email);
        smm.setSubject("AUSTOJ注册验证");
        smm.setText("您的验证链接为:"+mailPrefix+"token="+token);
        // 发送邮件
        taskExecutor.execute(() -> {
            javaMailSender.send(smm);
        });
        logger.info("发送邮件成功:{}",email);
        jedisClient.setex(token,3600*2,email);
    }
}
