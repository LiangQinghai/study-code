package cn.liangqinghai.study.mbp.utils.modelmapper.json;

import com.alibaba.fastjson.JSONArray;
import org.modelmapper.internal.Errors;
import org.modelmapper.spi.ConditionalConverter;
import org.modelmapper.spi.MappingContext;

/**
 * @author LiangQinghai
 * @Title JsonArrayToJsonArrayConverter
 * @ProjectName study-code
 * @Description
 * @date 2020/5/20 20:39
 */
public class JsonArrayToJsonArrayConverter implements ConditionalConverter<JSONArray, JSONArray> {
    @Override
    public MatchResult match(Class<?> sourceType, Class<?> destinationType) {
        return JSONArray.class.isAssignableFrom(sourceType)
                && JSONArray.class.isAssignableFrom(destinationType)
                ? MatchResult.FULL : MatchResult.NONE;
    }

    @Override
    public JSONArray convert(MappingContext<JSONArray, JSONArray> context) {

        if (context.getSource() == null) {
            return null;
        } else if (context.getSourceType().equals(context.getDestinationType())) {
            JSONArray source = context.getSource();
            return (JSONArray) source.clone();
        } else {
            throw new Errors()
                    .addMessage("不支持的类型，[%s -> %s]",
                            context.getSourceType().getName(),
                            context.getDestinationType().getName())
                    .toMappingException();
        }

    }
}
