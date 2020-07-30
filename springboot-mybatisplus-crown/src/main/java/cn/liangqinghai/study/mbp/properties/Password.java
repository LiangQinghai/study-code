package cn.liangqinghai.study.mbp.properties;


/**
 * @author LiangQinghai
 * @Title Password
 * @ProjectName study-code
 * @Description
 * @date 2020/5/20 9:52
 */
public class Password {

    private int maxRetryCount = 10;

    public int getMaxRetryCount() {
        return maxRetryCount;
    }

    public Password setMaxRetryCount(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
        return this;
    }
}
