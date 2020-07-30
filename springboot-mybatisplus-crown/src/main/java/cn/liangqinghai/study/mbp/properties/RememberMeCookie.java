package cn.liangqinghai.study.mbp.properties;


/**
 * @author LiangQinghai
 * @Title RemenberMeCookie
 * @ProjectName study-code
 * @Description
 * @date 2020/5/20 17:58
 */
public class RememberMeCookie {

    private String domain;

    private String path;

    private boolean httpOnly;

    private int maxAge;

    public String getDomain() {
        return domain;
    }

    public RememberMeCookie setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public String getPath() {
        return path;
    }

    public RememberMeCookie setPath(String path) {
        this.path = path;
        return this;
    }

    public boolean isHttpOnly() {
        return httpOnly;
    }

    public RememberMeCookie setHttpOnly(boolean httpOnly) {
        this.httpOnly = httpOnly;
        return this;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public RememberMeCookie setMaxAge(int maxAge) {
        this.maxAge = maxAge;
        return this;
    }
}
