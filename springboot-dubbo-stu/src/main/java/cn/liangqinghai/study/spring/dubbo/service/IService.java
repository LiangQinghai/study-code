package cn.liangqinghai.study.spring.dubbo.service;

import cn.liangqinghai.study.spring.dubbo.beans.MethodUriMappingBean;

import java.util.List;

/**
 * @author LiangQinghai
 * @title IService
 * @projectName study-code
 * @description
 * @date 2020/7/30 19:36
 */
public interface IService {

    /**
     * url
     *
     * @param methodUriMappingBeans
     * @return
     */
    boolean reportRequestMapping(List<MethodUriMappingBean> methodUriMappingBeans);

}
