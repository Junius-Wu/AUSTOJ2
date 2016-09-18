package cn.edu.aust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 生成验证码
 * @author Niu Li
 * @date 2016/9/17
 */
@Controller
public class CodeController {
    private int width = 90;//验证码宽度
    private int height = 40;//验证码高度
    private int codeCount = 4;//验证码个数
    private int lineCount = 19;//混淆线个数

    private char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    /**
     * 具体获取验证码的方法
     * @throws IOException
     */
    @RequestMapping(value = "/codeValidate",method = RequestMethod.GET)
    public void getCode(HttpServletRequest request,
                        HttpServletResponse response) throws IOException {
        //定义随机数类
        Random r = new Random();
        //定义存储验证码的类
        StringBuilder builderCode = new StringBuilder();
        //定义画布
        BufferedImage buffImg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //得到画笔
        Graphics g = buffImg.getGraphics();
        //1.设置颜色,画边框
        g.setColor(new Color(0.4235f,0.5765f,0.5647f,1f));
        g.drawRect(0,0,width,height);
        //2.设置颜色,填充内部
        g.setColor(new Color(0.4235f,0.5765f,0.5647f,1f));
        g.fillRect(1,1,width-2,height-2);
        //3.设置干扰线
        g.setColor(Color.gray);
        for (int i = 0; i < lineCount; i++) {
            g.drawLine(r.nextInt(width),r.nextInt(width),r.nextInt(width),r.nextInt(width));
        }
        //4.设置验证码
        g.setColor(Color.blue);
        //4.1设置验证码字体
        g.setFont(new Font("宋体",Font.BOLD|Font.ITALIC,30));
        for (int i = 0; i < codeCount; i++) {
            char c = codeSequence[r.nextInt(codeSequence.length)];
            builderCode.append(c);
            g.drawString(c+"",15*(i+1),30);
        }
        //5.输出到屏幕
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(buffImg,"png",sos);
        //6.保存到session中
        HttpSession session = request.getSession();
        session.setAttribute("codeValidate",builderCode.toString());
        //7.禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/png");
        //8.关闭sos
        sos.close();
    }
}
