package cn.edu.aust.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

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
    @Resource
    private StringRedisTemplate redisTemplate;

    private static Logger logger = LoggerFactory.getLogger(MailService.class);
    /**
     * 注册后发送验证邮件
     * @param email 邮箱
     */
    public void sendRegister(String email){
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
        redisTemplate.opsForValue().set(token,email,2L, TimeUnit.HOURS);
    }
}
