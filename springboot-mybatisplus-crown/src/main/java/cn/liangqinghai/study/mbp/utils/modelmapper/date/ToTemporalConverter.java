package cn.liangqinghai.study.mbp.utils.modelmapper.date;

import org.modelmapper.Converter;
import org.modelmapper.internal.Errors;
import org.modelmapper.spi.ConditionalConverter;
import org.modelmapper.spi.MappingContext;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Date;

/**
 * @author LiangQinghai
 * @Title ToTemporalConverter
 * @ProjectName study-code
 * @Description
 * @date 2020/5/20 19:37
 */
public class ToTemporalConverter implements ConditionalConverter<Object, Temporal> {

    private final DateModuleConfig config;
    private final LocalDateTimeConverter localDateTimeConverter = new LocalDateTimeConverter();
    private final LocalDateConverter localDateConverter = new LocalDateConverter();
    private final InstantConverter instantConverter = new InstantConverter();

    public ToTemporalConverter(DateModuleConfig config) {
        this.config = config;
    }

    @Override
    public MatchResult match(Class<?> sourceType, Class<?> destinationType) {
        return Temporal.class.isAssignableFrom(destinationType) ? MatchResult.FULL : MatchResult.NONE;
    }

    @Override
    public Temporal convert(MappingContext<Object, Temporal> context) {

        Class<Temporal> destinationType = context.getDestinationType();

        if (LocalDateTime.class.equals(destinationType)) {
            return localDateTimeConverter.convert(context);
        } else if (LocalDate.class.equals(destinationType)) {
            return localDateConverter.convert(context);
        } else if (Instant.class.equals(destinationType)) {
            return instantConverter.convert(context);
        } else {
            throw new Errors()
                    .addMessage("不支持的类型[%s -> %s]",
                            context.getSourceType().getName(), destinationType.getName())
                    .toMappingException();
        }

    }

    private LocalDate convertLocalDate(MappingContext<?, ?> mappingContext) {

        Object source = mappingContext.getSource();
        Class<?> aClass = source.getClass();

        if (aClass.equals(String.class)) {
            return LocalDate.parse((String)source,
                    DateTimeFormatter.ofPattern(config.getDatePattern()));
        }

        return convertInstant(mappingContext).atZone(config.getZoneId()).toLocalDate();

    }

    private LocalDateTime convertLocalDateTime(MappingContext<?, ?> mappingContext) {

        Object source = mappingContext.getSource();
        Class<?> aClass = source.getClass();

        if (aClass.equals(String.class)) {
            return LocalDateTime.parse((String) source,
                    DateTimeFormatter.ofPattern(config.getDateTimePattern()));
        }

        return convertInstant(mappingContext).atZone(config.getZoneId()).toLocalDateTime();

    }

    private Instant convertInstant(MappingContext<?, ?> mappingContext) {

        Object source = mappingContext.getSource();
        Class<?> aClass = source.getClass();

        if (aClass.equals(String.class)) {
            return LocalDateTime.parse((String) source,
                    DateTimeFormatter.ofPattern(config.getDateTimePattern()))
                    .atZone(config.getZoneId())
                    .toInstant();
        } else if (Date.class.isAssignableFrom(aClass)) {
            return Instant.ofEpochMilli(((Date) source).getTime());
        } else if (Calendar.class.isAssignableFrom(aClass)) {
            return Instant.ofEpochMilli(((Calendar) source).getTime().getTime());
        } else if (Number.class.isAssignableFrom(aClass)) {
            return Instant.ofEpochMilli(((Number) source).longValue());
        } else {
            throw new Errors()
                    .addMessage("不支持的类型[%s -> %s]",
                            aClass.getName(), mappingContext.getDestinationType().getName())
                    .toMappingException();
        }

    }

    private class LocalDateTimeConverter implements Converter<Object, Temporal> {
        @Override
        public Temporal convert(MappingContext<Object, Temporal> context) {
            return convertLocalDateTime(context);
        }
    }

    private class LocalDateConverter implements Converter<Object, Temporal> {
        @Override
        public Temporal convert(MappingContext<Object, Temporal> context) {
            return convertLocalDate(context);
        }
    }

    private class InstantConverter implements Converter<Object, Temporal> {
        @Override
        public Temporal convert(MappingContext<Object, Temporal> context) {
            return convertInstant(context);
        }
    }

}
