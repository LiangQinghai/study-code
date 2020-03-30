package cn.liangqinghai.study.web.rest.handlers;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author LiangQinghai
 * @Title GlobalErrorHandler
 * @ProjectName study-code
 * @Description
 * @date 3/30/2020 4:28 PM
 */
@Provider
@Slf4j
public class GlobalErrorHandler implements ExceptionMapper<Exception> {

    @Context
    private HttpServletRequest request;

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(Exception exception) {
        return null;
    }
}
