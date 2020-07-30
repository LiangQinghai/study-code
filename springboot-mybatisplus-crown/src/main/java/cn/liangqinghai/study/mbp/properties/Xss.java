package cn.liangqinghai.study.mbp.properties;

import java.util.List;

/**
 * @author LiangQinghai
 * @Title Xss
 * @ProjectName study-code
 * @Description
 * @date 2020/5/20 9:54
 */
public class Xss {

    private boolean enable = true;

    private List<String> excludeField;

    private List<String> excludeUrls;

    public boolean isEnable() {
        return enable;
    }

    public Xss setEnable(boolean enable) {
        this.enable = enable;
        return this;
    }

    public List<String> getExcludeField() {
        return excludeField;
    }

    public Xss setExcludeField(List<String> excludeField) {
        this.excludeField = excludeField;
        return this;
    }

    public List<String> getExcludeUrls() {
        return excludeUrls;
    }

    public Xss setExcludeUrls(List<String> excludeUrls) {
        this.excludeUrls = excludeUrls;
        return this;
    }
}
