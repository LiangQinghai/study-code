package cn.liangqinghai.study.mbp.utils.modelmapper.date;

import java.time.ZoneId;

/**
 * @author LiangQinghai
 * @Title DateModuleConfig
 * @ProjectName study-code
 * @Description
 * @date 2020/5/20 19:29
 */
public class DateModuleConfig {

    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";

    private String datePattern = DEFAULT_DATE_PATTERN;

    private String dateTimePattern = DEFAULT_DATE_TIME_PATTERN;

    private String timePattern = DEFAULT_TIME_PATTERN;

    private ZoneId zoneId = ZoneId.systemDefault();

    public static String getDefaultDatePattern() {
        return DEFAULT_DATE_PATTERN;
    }

    public static String getDefaultDateTimePattern() {
        return DEFAULT_DATE_TIME_PATTERN;
    }

    public static String getDefaultTimePattern() {
        return DEFAULT_TIME_PATTERN;
    }

    public String getDatePattern() {
        return datePattern;
    }

    public DateModuleConfig setDatePattern(String datePattern) {
        this.datePattern = datePattern;
        return this;
    }

    public String getDateTimePattern() {
        return dateTimePattern;
    }

    public DateModuleConfig setDateTimePattern(String dateTimePattern) {
        this.dateTimePattern = dateTimePattern;
        return this;
    }

    public String getTimePattern() {
        return timePattern;
    }

    public DateModuleConfig setTimePattern(String timePattern) {
        this.timePattern = timePattern;
        return this;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    public DateModuleConfig setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
        return this;
    }
}
