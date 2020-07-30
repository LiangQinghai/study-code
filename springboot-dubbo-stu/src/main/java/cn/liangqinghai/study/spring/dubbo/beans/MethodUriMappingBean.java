package cn.liangqinghai.study.spring.dubbo.beans;

import java.io.Serializable;
import java.util.Set;

/**
 * @author LiangQinghai
 * @title MethodUriMappingBean
 * @projectName study-code
 * @description
 * @date 2020/7/30 19:38
 */
public class MethodUriMappingBean implements Serializable {

    private static final long serialVersionUID = 7048250266556567633L;

    private String categoryCode;

    private String method;

    private String className;

    private Set<String> uris;

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Set<String> getUris() {
        return uris;
    }

    public void setUris(Set<String> uris) {
        this.uris = uris;
    }
}
