package cn.edu.aust.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * 延迟日志记录器,得益于lambda表达式为需要时才执行
 *
 * @author Niu Li
 * @date 2016/9/18
 */
public class LoggerUtil {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(LoggerUtil.class);

        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int count = 1;
        infoIf(logger,//log
                () -> array.length > 6,//if
                () -> {//work
                    IntStream ss = Arrays.stream(array);
                    ss.map(num -> num + 1).forEach(System.out::println);
                },
                () -> "数组");//msg
    }

    /**
     * info类型
     */
    public static void info(Logger logger, Supplier<String> message) {
        if (logger.isInfoEnabled()) {
            logger.info(message.get());
        }
    }

    public static void infoIf(Logger logger, Supplier<Boolean> condition, Supplier<String> message) {
        if (condition.get()) {
            logger.info(message.get());
        }
    }

    /**
     * condition成立则执行work
     */
    public static void infoIf(Logger logger, Supplier<Boolean> condition, Runnable work, Supplier<String> message) {
        if (condition.get()) {
            work.run();
            logger.info(message.get());
        }
    }

    /**
     * debug类型
     */
    public static void debug(Logger logger, Supplier<String> message) {
        if (logger.isDebugEnabled()) {
            logger.debug(message.get());
        }
    }

    /**
     * error类型
     */
    public static void error(Logger logger, Supplier<String> message) {
        if (logger.isErrorEnabled()) {
            logger.error(message.get());
        }
    }

    public static void error(Logger logger, Throwable e, Supplier<String> message) {
        if (logger.isErrorEnabled()) {
            logger.error(message.get(), e);
        }
    }

    /**
     * warn类型
     */
    public static void warn(Logger logger, Supplier<String> message) {
        if (logger.isWarnEnabled()) {
            logger.warn(message.get());
        }
    }

    public static void warn(Logger logger, Throwable e, Supplier<String> message) {
        if (logger.isWarnEnabled()) {
            logger.warn(message.get(), e);
        }
    }
}
