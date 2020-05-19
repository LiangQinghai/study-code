package cn.liangqinghai.study.mbp.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author Mr.Liang
 * @date 2020/5/19
 */
@ConfigurationProperties(prefix = MbpProperties.MBP)
@Data
public class MbpProperties {

    public static final String MBP = "mbp";

    @NestedConfigurationProperty
    private Demo demo;

}
