package cn.liangqinghai.study.mbp.properties;


/**
 * @author LiangQinghai
 * @Title Email
 * @ProjectName study-code
 * @Description
 * @date 2020/5/20 9:56
 */
public class Email {

    private boolean enable = false;

    private String send;

    public boolean isEnable() {
        return enable;
    }

    public Email setEnable(boolean enable) {
        this.enable = enable;
        return this;
    }

    public String getSend() {
        return send;
    }

    public Email setSend(String send) {
        this.send = send;
        return this;
    }
}
