package cn.liangqinghai.study.mbp.utils.modelmapper.date;

import org.modelmapper.internal.Errors;
import org.modelmapper.spi.ConditionalConverter;
import org.modelmapper.spi.MappingContext;

import java.time.temporal.Temporal;

/**
 * @author LiangQinghai
 * @Title TemporalToTemporalConverter
 * @ProjectName study-code
 * @Description
 * @date 2020/5/20 19:56
 */
public class TemporalToTemporalConverter implements ConditionalConverter<Temporal, Temporal> {
    @Override
    public MatchResult match(Class<?> sourceType, Class<?> destinationType) {
        return Temporal.class.isAssignableFrom(sourceType)
                && Temporal.class.isAssignableFrom(destinationType)
                ? MatchResult.FULL : MatchResult.NONE;
    }

    @Override
    public Temporal convert(MappingContext<Temporal, Temporal> context) {
        if (context.getSource() == null) {
            return null;
        } else if (context.getSourceType().equals(context.getDestinationType())) {
            return context.getSource();
        } else {
            throw new Errors()
                    .addMessage("不支持的数据类型, [%s -> %s]",
                            context.getSourceType().getName(),
                            context.getDestinationType().getName())
                    .toMappingException();
        }
    }
}
