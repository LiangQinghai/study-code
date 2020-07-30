package cn.liangqinghai.study.netty.rpc.bean;

import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Mr.Liang
 * @date 2020/3/18
 */
@Accessors(chain = true)
public class InvokeMessage implements Serializable {

    private static final long serialVersionUID = -2138891700541974138L;

    private String className;

    private String methodName;

    private Class<?>[] parameterTypes;

    private Object[] parameterValues;

    public String getClassName() {
        return className;
    }

    public InvokeMessage setClassName(String className) {
        this.className = className;
        return this;
    }

    public String getMethodName() {
        return methodName;
    }

    public InvokeMessage setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public InvokeMessage setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
        return this;
    }

    public Object[] getParameterValues() {
        return parameterValues;
    }

    public InvokeMessage setParameterValues(Object[] parameterValues) {
        this.parameterValues = parameterValues;
        return this;
    }
}
