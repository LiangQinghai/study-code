package cn.liangqinghai.study.mbp.utils.modelmapper.opt;

import org.modelmapper.internal.util.MappingContextHelper;
import org.modelmapper.spi.ConditionalConverter;
import org.modelmapper.spi.MappingContext;

import java.util.Optional;

/**
 * @author LiangQinghai
 * @Title ToOptionalConverter
 * @ProjectName study-code
 * @Description
 * @date 2020/5/20 20:50
 */
public class ToOptionalConverter implements ConditionalConverter<Object, Optional<Object>> {
    @Override
    public MatchResult match(Class<?> sourceType, Class<?> destinationType) {
        return (!Optional.class.equals(sourceType) && Optional.class.equals(destinationType))
                ? MatchResult.FULL : MatchResult.NONE;
    }

    @Override
    public Optional<Object> convert(MappingContext<Object, Optional<Object>> context) {

        if (context.getSource() == null) {
            return Optional.empty();
        }

        MappingContext<Object, ?> objectMappingContext = context.create(context.getSource(),
                MappingContextHelper.resolveDestinationGenericType(context));

        return Optional.ofNullable(context.getMappingEngine().map(objectMappingContext));

    }
}
