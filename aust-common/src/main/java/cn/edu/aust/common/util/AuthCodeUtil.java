package cn.edu.aust.common.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 生成验证码工具类
 * @author Niu Li
 * @date 2017/1/25
 */
public final class AuthCodeUtil {

    //验证码来源,去掉了混淆的O
    private static final char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    /**
     * 创建验证码
     * @param buffImg 放入的输出流
     * @param width 宽度
     * @param height 高度
     * @param lineCount 混淆线条数
     * @param codeCount 验证码数
     * @return 验证码
     */
    public static String createCodeImage(BufferedImage buffImg,int width,int height,int lineCount,int codeCount){
        //定义随机数类
        Random r = new Random();
        //定义存储验证码的类
        StringBuilder builderCode = new StringBuilder();
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
        return builderCode.toString();
    }
}
