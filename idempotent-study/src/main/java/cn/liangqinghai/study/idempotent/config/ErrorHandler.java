package cn.liangqinghai.study.idempotent.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author LiangQinghai
 * @Title ErrorHandler
 * @ProjectName study-code
 * @Description
 * @date 3/26/2020 10:38 AM
 */
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object exception(Exception ex) {
        return new Object(){
            private Integer code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            private String message = ex.getMessage();
            public String getMessage() {
                return message;
            }
            public Integer getCode() {
                return code;
            }
        };
    }

}
