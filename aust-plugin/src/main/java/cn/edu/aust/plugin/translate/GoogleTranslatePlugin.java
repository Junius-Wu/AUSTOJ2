package cn.edu.aust.plugin.translate;

import cn.edu.aust.common.util.JavaScriptUtil;
import cn.edu.aust.common.util.WebUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.jvm.hotspot.tools.SysPropsDumper;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 谷歌翻译工具类
 * 参考 https://github.com/lsj9383/translate-set
 * @author Niu Li
 * @since 2017/2/12
 */
public class GoogleTranslatePlugin {

    private static Logger logger = LoggerFactory.getLogger(GoogleTranslatePlugin.class);
    /**
     * 请求参数合集
     */
    private static final Map<String,Object> params = new HashMap<String,Object>();
    /**
     * 请求地址
     */
    private static final String GOOGLE_TRANSLATE = "http://translate.google.cn/translate_a/single";

    public static void main(String[] args) throws ScriptException, NoSuchMethodException {
        GoogleTranslatePlugin plugin = new GoogleTranslatePlugin();
        String res = plugin.Trans("zh-CN", "en", "你好啊");
        System.out.println(res);
    }

//    静态参数,不变化
    static{
        params.put("client", "t");
        params.put("hl", "zh-CN");
        params.put("dt", "at");
        params.put("dt", "bd");
        params.put("dt", "ex");
        params.put("dt", "ld");
        params.put("dt", "md");
        params.put("dt", "qca");
        params.put("dt", "rw");
        params.put("dt", "rm");
        params.put("dt", "ss");
        params.put("dt", "t");
        params.put("ie", "UTF-8");
        params.put("oe", "UTF-8");
        params.put("source", "btn");
        params.put("srcrom", "1");
        params.put("ssel", "0");
        params.put("tsel", "0");
        params.put("kc", "11");
    }

    /**
     * 获取插件名称
     */
    public String getPluginName() {
        return "googleTranslatePlugin";
    }

    /**
     * 谷歌翻译服务
     * @param from 语言 汉语为zh-CN 英语为en
     * @param targ 要翻译为的语言,同上
     * @param query 要翻译的内容
     * @return 翻译结果
     */
    public String Trans(String from, String targ, String query) {
        String result = null;
        try {
            params.put("sl", from);
            params.put("tl", targ);
            params.put("tk", tk(query));
            params.put("q", query);
            String jsonResult = WebUtils.get(GOOGLE_TRANSLATE,params);
            result = parseResult(jsonResult);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("谷歌翻译错误",e);
            result = "";
        }
        return result;
    }

    /**
     * 解析谷歌返回的json串
     * @param jsonResult json串
     * @return 翻译结果
     */
    private String parseResult(String jsonResult) {
        StringBuilder result = new StringBuilder();
        try {
            JSONArray jsonArray = JSON.parseArray(jsonResult);
            JSONArray segments = jsonArray.getJSONArray(0);

            for(int i=0; i<segments.size(); i++){
                result.append(segments.getJSONArray(i).getString(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("谷歌翻译结果解析错误",e);
            return ""; //解析失败就返回空
        }
        return new String(result);
    }

    /**
     * 生成谷验证签名
     * @param val 要翻译的字串
     * @return 签名结果
     * @throws ScriptException 抛出异常
     * @throws NoSuchMethodException 抛出异常
     */
    private String tk(String val) throws ScriptException, NoSuchMethodException {
        String script ="function tk(a) {"
                +"var TKK = ((function() {var a = 561666268;var b = 1526272306;return 406398 + '.' + (a + b); })());\n"
                +"function b(a, b) { for (var d = 0; d < b.length - 2; d += 3) { var c = b.charAt(d + 2), c = 'a' <= c ? c.charCodeAt(0) - 87 : Number(c), c = '+' == b.charAt(d + 1) ? a >>> c : a << c; a = '+' == b.charAt(d) ? a + c & 4294967295 : a ^ c } return a }\n"
                +"for (var e = TKK.split('.'), h = Number(e[0]) || 0, g = [], d = 0, f = 0; f < a.length; f++) {"
                +"var c = a.charCodeAt(f);"
                +"128 > c ? g[d++] = c : (2048 > c ? g[d++] = c >> 6 | 192 : (55296 == (c & 64512) && f + 1 < a.length && 56320 == (a.charCodeAt(f + 1) & 64512) ? (c = 65536 + ((c & 1023) << 10) + (a.charCodeAt(++f) & 1023), g[d++] = c >> 18 | 240, g[d++] = c >> 12 & 63 | 128) : g[d++] = c >> 12 | 224, g[d++] = c >> 6 & 63 | 128), g[d++] = c & 63 | 128)"
                +"}"
                +"a = h;"
                +"for (d = 0; d < g.length; d++) a += g[d], a = b(a, '+-a^+6');"
                +"a = b(a, '+-3^+b+-f');"
                +"a ^= Number(e[1]) || 0;"
                +"0 > a && (a = (a & 2147483647) + 2147483648);"
                +"a %= 1E6;"
                +"return a.toString() + '.' + (a ^ h)\n"
                +"}";

        ScriptEngine engine = JavaScriptUtil.getEngine();
        engine.eval(script);
        Invocable inv = (Invocable) engine;
        return (String) inv.invokeFunction("tk", val);
    }
}
