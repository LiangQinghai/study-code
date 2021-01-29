package cn.liangqinghai.study.webservice;

import cn.liangqinghai.study.webservice.service.DemoImpl;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

/**
 * <p>Project name: study-code</p>
 * <p>Package name: cn.liangqinghai.study.webservice</p>
 * <p>File name: CetusResult</p>
 * <div>
 * <h3>Description: </h3>
 * TODO
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/1/26 20:30
 */
@XmlRootElement(name="result")
@XmlAccessorType(XmlAccessType.FIELD)
public class CetusResult implements Serializable {

    private static final long serialVersionUID = -2939076761773021341L;
    /**
     * 响应码
     */
    Integer code  = 200;
    /**
     * 错误消息
     */
    String  msg   = null;
    /**
     * 国际化参数码，需要国际化才返回
     */
    String[] parameterArgs = null;
    /**
     * 响应结果
     */
    @XmlElementWrapper(name="values")    // 在集合外包一层，名为items
    @XmlElement(name="value")
    List<DemoImpl.Bean>       value = null;
    /**
     * 总记录数，只有查询参数中包含pager时才会返回count，否则为-1
     */
    int     count = -1;

    @Override
    public String toString() {
        String ret = "code:" + code + "|msg:" + msg + "|count:" + count + "\n";
        if (value != null) {
            ret += "value:" + value.toString() + "";
            if (value instanceof List) {
                ret += "|size:" + ((List) value).size();
            }
        }
        return ret;
    }

    /**
     * 日志输出信息
     * @return
     */
    public String toLogString() {
        return "code:" + code + "|msg:" + msg + "|count:" + count;
    }


    public CetusResult(Integer code, String msg, List<DemoImpl.Bean> value) {
        super();
        this.code = code;
        this.msg = msg;
        this.value = value;
    }


    /**
     * 返回成功结果
     *
     * @param e   结果
     * @return 成功结果
     */
    public static CetusResult success(List<DemoImpl.Bean> e) {
        CetusResult result = new CetusResult();
        result.setCode(200);
        result.setValue(e);
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public CetusResult setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public CetusResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String[] getParameterArgs() {
        return parameterArgs;
    }

    public CetusResult setParameterArgs(String[] parameterArgs) {
        this.parameterArgs = parameterArgs;
        return this;
    }

    public List<DemoImpl.Bean> getValue() {
        return value;
    }

    public CetusResult setValue(List<DemoImpl.Bean> value) {
        this.value = value;
        return this;
    }

    public int getCount() {
        return count;
    }

    public CetusResult setCount(int count) {
        this.count = count;
        return this;
    }

    public CetusResult() {
        super();
    }
}
