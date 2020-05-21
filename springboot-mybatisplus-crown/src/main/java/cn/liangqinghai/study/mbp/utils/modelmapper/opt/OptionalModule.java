package cn.liangqinghai.study.mbp.utils.modelmapper.opt;

import org.modelmapper.ModelMapper;
import org.modelmapper.Module;
import org.modelmapper.spi.ConditionalConverter;

import java.util.List;

/**
 * @author LiangQinghai
 * @Title OptionalModule
 * @ProjectName study-code
 * @Description
 * @date 2020/5/20 20:47
 */
public class OptionalModule implements Module {
    @Override
    public void setupModule(ModelMapper modelMapper) {

        List<ConditionalConverter<?, ?>> converters = modelMapper.getConfiguration().getConverters();
        converters.add(0, new FromOptionalConverter());
        converters.add(0, new ToOptionalConverter());

    }
}
