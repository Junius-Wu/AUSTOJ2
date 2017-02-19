package cn.edu.aust.judger.core;

import cn.edu.aust.judger.proto.judgeResponse;
import cn.edu.aust.judger.util.Constant;
import cn.edu.aust.judger.util.LanguageUtil;
import cn.edu.aust.judger.util.PosCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * 程序编译器, 用于编译用户提交的代码.
 * 
 * @author Haozhe Xie
 */
@Slf4j
public class Compiler {
    //下搬替换依赖注入
    private Runner runner = new Runner();
    /**
     * 获取编译命令
     * @param sourcePath 临时代码的绝对路径
     * @param language 语言
     * @return 编译命令
     */
	public String getCompileCommandLine(String sourcePath, LanguageUtil.Language language) {
        return language.getCompileCommand().replaceAll("\\{filename\\}", sourcePath);
	}

    /**
     * 得到编译文件的日志路径
     * @param sourcePath 临时代码的绝对路径
     * @return 编译日志路径
     */
	public String getCompileLogPath(String sourcePath) {
		return String.format("%s-compile.log", sourcePath);
	}

    /**
     * 获取编译结果
     * @param commandLine 编译命令
     * @param compileLogPath 编译日志路径
     * @return null成功,否则返回错误信息
     */
	public judgeResponse getCompileResult(String commandLine, String compileLogPath) {
        judgeResponse.Builder builder = judgeResponse.newBuilder();
		int timeLimit = 5000;
		int memoryLimit = 0;
		
		log.info("Start compiling with command: %s",commandLine);
		Map<String, Object> runningResult = runner.getRuntimeResult(commandLine, Constant.systemUsername,
                Constant.systemPassword, null, compileLogPath, timeLimit, memoryLimit);
		boolean isSuccessful = false;
		if ( runningResult != null ) {
			int exitCode = (Integer)runningResult.get("exitCode");
			isSuccessful = exitCode == 0;
		}
		if (isSuccessful){
		    return null;
        }
        builder.setRuntimeResult(getCompileOutput(compileLogPath))
                .setExitCode(PosCode.COMPILE_ERROR.getStatus());
		return builder.build();
	}
	
	/**
	 * 获取编译日志内容.
	 * @param compileLogPath - 编译日志路径
	 * @return 编译日志内容
	 */
	public String getCompileOutput(String compileLogPath) {
        File logFile = new File(compileLogPath);
        if (!logFile.exists()){
            return "no exits !";
        }
		String compileLog = "";
		try {
			compileLog = FileUtils.readFileToString(logFile, Charset.forName("UTF-8"));
		} catch (Exception ex) {
			// Do nothing
		}
		log.info("编译信息:%s",compileLog);
		return compileLog;
	}
	
}
