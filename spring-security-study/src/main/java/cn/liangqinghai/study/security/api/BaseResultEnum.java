package cn.liangqinghai.study.security.api;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.common.api</p>
 * <p>File name: BaseResultEnum</p>
 * <div>
 * <h3>Description: </h3>
 * 基础结果响应
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/19 14:51
 */
public enum BaseResultEnum implements IResult {

    // 定义
    OK(200, "成功"),
    FAILED(500, "系统繁忙，请稍后再试"),
    UNAUTHORIZED(401, "用户未登录"),
    FORBIDDEN(403, "没有操作权限")

    ;
    private final Integer code;
    private final String message;

    BaseResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }
}
