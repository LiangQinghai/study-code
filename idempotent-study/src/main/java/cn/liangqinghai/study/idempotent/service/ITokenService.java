package cn.liangqinghai.study.idempotent.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LiangQinghai
 * @Title ITokenService
 * @ProjectName study-code
 * @Description
 * @date 3/26/2020 10:34 AM
 */
public interface ITokenService {

    /**
     * 设置token
     *
     * @return
     */
    String getToken();

    /**
     * 检测token
     *
     * @param request
     * @return
     */
    boolean checkToken(HttpServletRequest request);

}
