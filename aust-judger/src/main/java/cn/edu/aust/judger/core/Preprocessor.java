package cn.edu.aust.judger.core;

import cn.edu.aust.judger.exception.CreateDirectoryException;
import cn.edu.aust.judger.exception.NoTestCaseException;
import cn.edu.aust.judger.model.Checkpoint;
import cn.edu.aust.judger.util.Constant;
import cn.edu.aust.judger.util.DigestUtils;
import cn.edu.aust.judger.util.LanguageUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 预处理器, 用于完成评测前准备工作.
 * 
 * @author Haozhe Xie
 */
@Slf4j
public class Preprocessor {

    /**
     * 测试数据结尾
     */
	private static final String[] caseStyle = {"in","out"};

    /**
     * 创建测试代码至本地磁盘临时目录.(需要配合定期清理策略)
     * @param sourceCode 源码
     * @param tempWordDir 邻水目录
     * @param language 语言
     * @return 临时目录下文件绝对路径,不包括扩展名
     * @throws Exception 临时文件路径不存在
     */
	public String createTestCode(String sourceCode,String tempWordDir,LanguageUtil.Language language) throws Exception {
		File workDirFile = new File(tempWordDir);
		if ( !workDirFile.exists() && !workDirFile.mkdirs() ) {
			throw new CreateDirectoryException("Failed to create directory: " + tempWordDir);
		}
		//生成随机类名
		String baseFileName = DigestUtils.getRandomString(12, DigestUtils.Mode.ALPHA);
		String code = replaceClassName(language, sourceCode, baseFileName);
		String codeFilePath = String.format("%s/%s.%s",
                 tempWordDir, baseFileName, language.getSuffix());
		FileUtils.write(new File(codeFilePath), code,"UTF-8");
		return codeFilePath.substring(0,codeFilePath.lastIndexOf("."));
	}
	
	/**
	 * 替换部分语言中的类名(如Java), 以保证正常通过编译.
	 * @param language - 编程语言对象
	 * @param code - 待替换的代码
	 * @param newClassName - 新的类名
	 */
	private String replaceClassName(LanguageUtil.Language language, String code, String newClassName) {
		if ( !language.getLanguageName().equalsIgnoreCase("Java") ) {
			return code;
		}
		return code.replaceAll("class[ \n]+Main", "class " + newClassName);
	}
	
	/**
	 * 从指定文件夹读取评测数据.
	 * @param problemId - 试题的唯一标识符
	 * @throws Exception 不存在数据抛出异常
	 */
	public List<Checkpoint> fetchTestPoints(long problemId) throws Exception {
		//获取全部测试数据
        String baseCase = Constant.testCaseDirectory + File.separator + String.valueOf(problemId);
        File file = new File(baseCase);
		if (!file.exists() || !file.isDirectory()){
		    throw new NoTestCaseException("该题目不存在的测试数据");
        }
        Collection<File> testCases = FileUtils.listFiles(file,caseStyle,false);
		if (testCases.isEmpty()){
            throw new NoTestCaseException("该题目不存在的测试数据");
        }

		List<Checkpoint> checkpoints = new ArrayList<>(testCases.size());
        for (int i = 1; i < testCases.size()+1; i++) {
            Checkpoint tempPoint = new Checkpoint();
            tempPoint.setProblemId(problemId);
            tempPoint.setCheckpointId(i);
            tempPoint.setInput(baseCase+File.separator+"data"+i+".in");
            tempPoint.setOutput(baseCase+File.separator+"data"+i+".out");
            tempPoint.setScore(10);//暂时不计分
            tempPoint.setExactlyMatch(true);
            checkpoints.add(tempPoint);
        }
        return checkpoints;
	}
}
