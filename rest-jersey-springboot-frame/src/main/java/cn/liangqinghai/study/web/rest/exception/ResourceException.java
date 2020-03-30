package cn.liangqinghai.study.web.rest.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * @author LiangQinghai
 * @Title ResourceException
 * @ProjectName study-code
 * @Description
 * @date 3/30/2020 4:44 PM
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceException extends RuntimeException{

    private static final long serialVersionUID = -1129548840477937598L;

    private Integer errorCode = HttpStatus.INTERNAL_SERVER_ERROR.value();

}
