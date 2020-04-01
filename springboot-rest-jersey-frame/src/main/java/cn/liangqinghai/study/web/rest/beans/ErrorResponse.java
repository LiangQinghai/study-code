package cn.liangqinghai.study.web.rest.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author LiangQinghai
 * @Title ErrorResponse
 * @ProjectName study-code
 * @Description
 * @date 3/30/2020 4:31 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ErrorResponse {

    private Integer errorCode;

    private String errorMessage;

}
