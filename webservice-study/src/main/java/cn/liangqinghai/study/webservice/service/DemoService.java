package cn.liangqinghai.study.webservice.service;

import cn.liangqinghai.study.webservice.CetusResult;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Project name: study-code</p>
 * <p>Package name: cn.liangqinghai.study.webservice.service</p>
 * <p>File name: DemoService</p>
 * <div>
 * <h3>Description: </h3>
 * TODO
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/1/25 10:03
 */
@WebService
public interface DemoService {
    @WebMethod
    String hello();
    @WebMethod
    ArrayList<DemoImpl.Bean> beans();
    @WebMethod
    HashMap<String, Object> map();
    @WebMethod
    CetusResult dto();
    @WebMethod(operationName = "queryByName")
    CetusResult dto(String name);
}
