package cn.edu.aust.judger.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据语言获取其编译命令和执行命令
 * @author Niu Li
 * @since 2017/2/19
 */
public final class LanguageUtil {

    private static final Map<String,Language> langMap = new HashMap<>(10);

    //主要不想关联到数据库,暂时先写死
    static {
        //java语言
        Language java = new Language("text/x-java","java",
                "javac {filename}.java","java -cp {filename}","java");
        langMap.put(java.getLanguageName(),java);
        //c语言
        Language c = new Language("text/x-csrc","C",
                "gcc -O2 -s -Wall -o {filename}.exe {filename}.c -lm","{filename}.exe","c");
        langMap.put(c.getLanguageName(),c);
        //C++
        Language cEnhance = new Language("text/x-c++src","C++",
                "g++ -O2 -s -Wall -std=c++11 -o {filename}.exe {filename}.cpp -lm","{filename}.exe","cpp");
        langMap.put(cEnhance.getLanguageName(),cEnhance);
    }

    /**
     * 获取对应语言的编译指令
     * @param  language 语言名称
     * @return 该语言实例
     */
    public static Language getLanguage(String language) {
        return langMap.get(language);
    }
    /**
     * 具体语言实体类
     */
    @Data
    @AllArgsConstructor
    public static class Language{
        private String languageSlug;
		private String languageName;
		private String compileCommand;
		private String runCommand;
		private String suffix;//文件后缀
    }
}
