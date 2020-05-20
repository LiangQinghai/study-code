package cn.liangqinghai.study.mbp.utils.modelmapper.date;

import org.modelmapper.Converter;
import org.modelmapper.internal.Errors;
import org.modelmapper.spi.ConditionalConverter;
import org.modelmapper.spi.MappingContext;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Date;

/**
 * @author LiangQinghai
 * @Title FromTemporalConverter
 * @ProjectName study-code
 * @Description
 * @date 2020/5/20 20:06
 */
public class FromTemporalConverter implements ConditionalConverter<Temporal, Object> {

    public final DateModuleConfig config;

    public FromTemporalConverter(DateModuleConfig config) {
        this.config = config;
    }

    @Override
    public MatchResult match(Class<?> sourceType, Class<?> destinationType) {
        return null;
    }

    @Override
    public Object convert(MappingContext<Temporal, Object> context) {
        return null;
    }

    private Object convertLocalDateTime(LocalDateTime source, MappingContext<?, ?> context) {

        Class<?> destinationType = context.getDestinationType();
        if (destinationType.equals(String.class)) {
            return DateTimeFormatter.ofPattern(config.getDateTimePattern())
                    .format(source);
        }
        Instant instant = source.atZone(config.getZoneId()).toInstant();
        return convertInstant(instant, context);

    }

    private Object convertInstant(Instant instant, MappingContext<?,?> context) {

        Class<?> destinationType = context.getDestinationType();

        if (destinationType.equals(String.class)) {
            return DateTimeFormatter.ofPattern(config.getDateTimePattern())
                    .withZone(config.getZoneId())
                    .format(instant);
        } else if (Date.class.isAssignableFrom(destinationType)) {
            return new Date(instant.toEpochMilli());
        } else if (Calendar.class.isAssignableFrom(destinationType)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(instant.toEpochMilli());
            return calendar;
        } else if (Long.class.isAssignableFrom(destinationType)) {
            return instant.toEpochMilli();
        } else if (BigDecimal.class.isAssignableFrom(destinationType)) {
            return new BigDecimal(instant.toEpochMilli());
        } else if (BigInteger.class.isAssignableFrom(destinationType)) {
            return BigInteger.valueOf(instant.toEpochMilli());
        } else {
            throw new Errors()
                    .addMessage("不支持的类型，[%s -> %s]",
                            context.getSourceType().getName(),
                            destinationType.getName())
                    .toMappingException();
        }

    }

    private  class LocalDateConverter implements Converter<Temporal, Object> {
        @Override
        public Object convert(MappingContext<Temporal, Object> context) {
            LocalDate source = (LocalDate) context.getSource();
            Class<Object> destinationType = context.getDestinationType();

            if (destinationType.equals(String.class)) {
                return DateTimeFormatter.ofPattern(config.getDatePattern())
                        .format(source);
            }

            LocalDateTime localDateTime = source.atStartOfDay();
            return convertLocalDateTime(localDateTime, context);
        }
    }

    private class LocalDateTimeConverter implements Converter<Temporal, Object> {
        @Override
        public Object convert(MappingContext<Temporal, Object> context) {
            return convertLocalDateTime((LocalDateTime) context.getSource(), context);
        }
    }

    private class InstantConverter implements Converter<Temporal, Object> {
        @Override
        public Object convert(MappingContext<Temporal, Object> context) {
            return convertInstant((Instant) context.getSource(), context);
        }
    }

}
