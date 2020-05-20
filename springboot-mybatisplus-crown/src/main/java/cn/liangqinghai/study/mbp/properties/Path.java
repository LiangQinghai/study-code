package cn.liangqinghai.study.mbp.properties;

import lombok.Data;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author LiangQinghai
 * @Title Path
 * @ProjectName study-code
 * @Description
 * @date 2020/5/20 9:41
 */
@Data
public class Path {

    private String filePath;

    private String resourceHandler;

    private String resourcePath;

    @NestedConfigurationProperty
    private Prefix prefix;

}
