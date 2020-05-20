package cn.liangqinghai.study.mbp.utils.modelmapper.date;

import org.modelmapper.ModelMapper;
import org.modelmapper.Module;
import org.modelmapper.spi.ConditionalConverter;

import java.util.List;

/**
 * @author LiangQinghai
 * @Title DateModule
 * @ProjectName study-code
 * @Description jdk8日期转换器
 * @date 2020/5/20 19:34
 */
public class DateModule implements Module {

    private final DateModuleConfig config;

    public DateModule() {
        this(new DateModuleConfig());
    }

    public DateModule(DateModuleConfig config) {
        this.config = config;
    }

    @Override
    public void setupModule(ModelMapper modelMapper) {
        List<ConditionalConverter<?, ?>> converters = modelMapper.getConfiguration().getConverters();
        converters.add(0, new FromTemporalConverter(config));
        converters.add(0, new ToTemporalConverter(config));
        converters.add(0, new TemporalToTemporalConverter());
    }
}
