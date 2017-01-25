package cn.edu.aust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.aust.common.util.CodeUtil;

/**
 * 验证码
 * @author Niu Li
 * @date 2017/1/25
 */
@Controller
public class CodeController {

    private final static int width = 90;//验证码宽度
    private final static int height = 40;//验证码高度
    private final static int codeCount = 4;//验证码个数
    private final static int lineCount = 19;//混淆线个数

    /**
     * 具体获取验证码的方法
     * @throws IOException 抛出异常
     */
    @GetMapping(value = "/codeValidate")
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //生成验证码
        BufferedImage buffImg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        String resultCode = CodeUtil.createCodeImage(buffImg,width,height,lineCount,codeCount);
        //保存到session中
        HttpSession session = request.getSession();
        session.setAttribute("codeValidate",resultCode);
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/png");
        //写回
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(buffImg,"png",sos);

    }
}
