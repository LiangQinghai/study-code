package cn.liangqinghai.study.mbp.utils.modelmapper.json;

import com.alibaba.fastjson.JSONObject;
import org.modelmapper.internal.Errors;
import org.modelmapper.spi.ConditionalConverter;
import org.modelmapper.spi.MappingContext;

/**
 * @author LiangQinghai
 * @Title JsonObjToJsonObjConverter
 * @ProjectName study-code
 * @Description
 * @date 2020/5/20 20:43
 */
public class JsonObjToJsonObjConverter implements ConditionalConverter<JSONObject, JSONObject> {
    @Override
    public MatchResult match(Class<?> sourceType, Class<?> destinationType) {
        return JSONObject.class.isAssignableFrom(sourceType)
                && JSONObject.class.isAssignableFrom(destinationType)
                ? MatchResult.FULL : MatchResult.NONE;
    }

    @Override
    public JSONObject convert(MappingContext<JSONObject, JSONObject> context) {

        if (context.getSource() == null) {
            return null;
        } else if (context.getSourceType().equals(context.getDestinationType())) {
            JSONObject source = context.getSource();
            return (JSONObject) source.clone();
        } else {

            throw new Errors()
                    .addMessage("不支持的类型，[%s -> %s]",
                            context.getSourceType().getName(),
                            context.getDestinationType().getName())
                    .toMappingException();

        }

    }
}
