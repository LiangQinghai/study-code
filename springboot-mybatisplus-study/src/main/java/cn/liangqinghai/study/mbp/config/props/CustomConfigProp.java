package cn.liangqinghai.study.mbp.config.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author LiangQinghai
 * @Title CustomConfigProp
 * @ProjectName study-code
 * @Description
 * @date 2020/5/27 10:44
 */
@ConfigurationProperties(prefix = "custom")
@Data
public class CustomConfigProp {

    @NestedConfigurationProperty
    private FreeMakerProp freeMaker;

}
