package cn.liangqinghai.study.mbp.utils.modelmapper.opt;

import org.modelmapper.spi.ConditionalConverter;
import org.modelmapper.spi.MappingContext;

import java.util.Optional;

/**
 * @author LiangQinghai
 * @Title FromOptionalConverter
 * @ProjectName study-code
 * @Description
 * @date 2020/5/20 20:49
 */
public class FromOptionalConverter implements ConditionalConverter<Optional<Object>, Object> {
    @Override
    public MatchResult match(Class<?> sourceType, Class<?> destinationType) {
        return (Optional.class.equals(sourceType) && !Optional.class.equals(destinationType))
                ? MatchResult.FULL
                : MatchResult.NONE;
    }

    @Override
    public Object convert(MappingContext<Optional<Object>, Object> mappingContext) {
        if (mappingContext.getSource() == null || !mappingContext.getSource().isPresent()) {
            return null;
        }

        MappingContext<Object, Object> propertyContext = mappingContext.create(
                mappingContext.getSource().get(), mappingContext.getDestinationType());
        return mappingContext.getMappingEngine().map(propertyContext);
    }
}
