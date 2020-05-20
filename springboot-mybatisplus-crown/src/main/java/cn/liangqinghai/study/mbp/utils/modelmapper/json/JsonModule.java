package cn.liangqinghai.study.mbp.utils.modelmapper.json;

import org.modelmapper.ModelMapper;
import org.modelmapper.Module;
import org.modelmapper.spi.ConditionalConverter;

import java.util.List;

/**
 * @author LiangQinghai
 * @Title JsonModule
 * @ProjectName study-code
 * @Description
 * @date 2020/5/20 20:36
 */
public class JsonModule implements Module {
    @Override
    public void setupModule(ModelMapper modelMapper) {

        List<ConditionalConverter<?, ?>> converters = modelMapper.getConfiguration().getConverters();

        converters.add(0, new JsonArrayToJsonArrayConverter());
        converters.add(0, new JsonObjToJsonObjConverter());

    }
}
