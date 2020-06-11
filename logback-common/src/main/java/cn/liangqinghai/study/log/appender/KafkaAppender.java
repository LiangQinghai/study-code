package cn.liangqinghai.study.log.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.AppenderBase;

/**
 * @author LiangQinghai
 * @Title KafkaAppender
 * @ProjectName study-code
 * @Description
 * @date 2020/6/9 10:47
 */
public class KafkaAppender extends AppenderBase<ILoggingEvent> {

    private String appName;

    private String kafkaHosts;

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setKafkaHosts(String kafkaHosts) {
        this.kafkaHosts = kafkaHosts;
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
        System.out.println("message" + eventObject.getFormattedMessage());
        LoggingEvent event = (LoggingEvent) eventObject;
        for (StackTraceElement element : event.getCallerData()) {
        }
    }
}
