package cn.liangqinghai.study.netty.rpc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Mr.Liang
 * @date 2020/3/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class InvokeMessage {

    private String className;

    private String methodName;

    private Class<?>[] parameterTypes;

    private Object[] parameterValues;

}
