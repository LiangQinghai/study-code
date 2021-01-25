package cn.liangqinghai.study.log;

import ch.qos.logback.classic.LoggerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author LiangQinghai
 * @Title DynamicChangeLog
 * @ProjectName study-code
 * @Description
 * @date 2020/4/21 20:04
 */
public class DynamicChangeLog {

    private static final Logger logger = LoggerFactory.getLogger(DynamicChangeLog.class);

    private final static Set<String> logLevelCheck = new HashSet<String>();
    static{
        logLevelCheck.add("OFF");
        logLevelCheck.add("ERROR");
        logLevelCheck.add("WARN");
        logLevelCheck.add("INFO");
        logLevelCheck.add("DEBUG");
        logLevelCheck.add("TRACE");
        logLevelCheck.add("ALL");
    }

    public static void main(String[] args) {


        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

        List<ch.qos.logback.classic.Logger> loggerList = context.getLoggerList();

//        loggerList.forEach(log -> logger.info("{} : \n {}", log.getName(), JSONUtil.toJsonPrettyStr(log)));
//
//        loggerList.forEach(log -> {
//            log.setLevel(Level.toLevel("DEBUG"));
//            log.debug("{} changed to {}.", log.getName(), log.getEffectiveLevel().levelStr);
//        });

        logger.debug("end...");

    }

}
