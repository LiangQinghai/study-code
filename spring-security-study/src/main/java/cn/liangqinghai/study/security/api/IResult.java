package cn.liangqinghai.study.security.api;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.common.api</p>
 * <p>File name: IResult</p>
 * <div>
 * <h3>Description: </h3>
 * 定义统一结果接口
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/19 14:49
 */
public interface IResult {

    /**
     * 获取响应码
     *
     * @return 响应码
     */
    Integer code();

    /**
     * 错误信息
     *
     * @return 错误信息
     */
    String message();

}
