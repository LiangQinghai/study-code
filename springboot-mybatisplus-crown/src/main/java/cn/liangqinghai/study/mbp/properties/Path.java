package cn.liangqinghai.study.mbp.properties;

import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author LiangQinghai
 * @Title Path
 * @ProjectName study-code
 * @Description
 * @date 2020/5/20 9:41
 */
public class Path {

    private String filePath;

    private String resourceHandler;

    private String resourcePath;

    @NestedConfigurationProperty
    private Prefix prefix;

    public String getFilePath() {
        return filePath;
    }

    public Path setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public String getResourceHandler() {
        return resourceHandler;
    }

    public Path setResourceHandler(String resourceHandler) {
        this.resourceHandler = resourceHandler;
        return this;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public Path setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
        return this;
    }

    public Prefix getPrefix() {
        return prefix;
    }

    public Path setPrefix(Prefix prefix) {
        this.prefix = prefix;
        return this;
    }
}
