package cn.liangqinghai.study.netty.rpc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Mr.Liang
 * @date 2020/3/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class InvokeMessage implements Serializable {

    private static final long serialVersionUID = -2138891700541974138L;

    private String className;

    private String methodName;

    private Class<?>[] parameterTypes;

    private Object[] parameterValues;

}
