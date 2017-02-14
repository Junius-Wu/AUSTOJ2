
import cn.edu.aust.plugin.translate.GoogleTranslatePlugin;

/**
 * @author Niu Li
 * @since 2017/2/14
 */
public class GoogleTranslanteTest {

    @org.junit.Test
    public void testTransLate() {
        GoogleTranslatePlugin plugin = new GoogleTranslatePlugin();
        String res = plugin.Trans("zh-CN", "en", "张笑笑");
        System.out.println(res);
    }
}
