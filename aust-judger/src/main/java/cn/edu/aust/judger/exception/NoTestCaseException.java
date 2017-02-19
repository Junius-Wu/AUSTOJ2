package cn.edu.aust.judger.exception;

/**
 * 不存在的测试例子
 * @author Niu Li
 * @since 2017/2/19
 */
public class NoTestCaseException extends Exception {

    public NoTestCaseException(String message) {
        super(message);
    }
}
