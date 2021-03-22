package cn.liangqinghai.study.security.api;


import java.io.Serializable;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.common.api</p>
 * <p>File name: ResultDTO</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/19 14:47
 */
public class ResultDTO<T> implements Serializable {

    private Integer code;

    private String message;

    private T data;

    protected ResultDTO() {

    }

    protected ResultDTO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResultDTO<T> success(T data) {
        return new ResultDTO<>(BaseResultEnum.OK.code(), BaseResultEnum.OK.message(), data);
    }

    public static <T> ResultDTO<T> failed(IResult result, T data) {
        return new ResultDTO<>(result.code(), result.message(), data);
    }

    public static <T> ResultDTO<T> failed(IResult result) {
        return failed(result, null);
    }

    public static <T> ResultDTO<T> unauthorized(T data) {
        return failed(BaseResultEnum.UNAUTHORIZED, data);
    }

    public static <T> ResultDTO<T> unauthorized() {
        return failed(BaseResultEnum.UNAUTHORIZED);
    }

    public static <T> ResultDTO<T> forbidden(T data) {
        return failed(BaseResultEnum.FORBIDDEN, data);
    }

    public static <T> ResultDTO<T> forbidden() {
        return failed(BaseResultEnum.FORBIDDEN);
    }

    public Integer getCode() {
        return code;
    }

    public ResultDTO<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultDTO<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResultDTO<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "ResultDTO{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
