package cn.edu.aust.common.util;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * 得到Java8中的js解析器
 *
 * @author Niu Li
 * @since 2017/2/12
 */
public abstract class JavaScriptUtil {

    private static final ScriptEngineManager engineManager = new ScriptEngineManager();

    /**
     * 返回默认引擎
     *
     * @return nashorn引擎
     */
    public static ScriptEngine getEngine() {
        return engineManager.getEngineByName("nashorn");
    }

}
