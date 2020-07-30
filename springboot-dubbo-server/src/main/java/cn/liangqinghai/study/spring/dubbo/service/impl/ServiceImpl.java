package cn.liangqinghai.study.spring.dubbo.service.impl;

import cn.liangqinghai.study.spring.dubbo.beans.MethodUriMappingBean;
import cn.liangqinghai.study.spring.dubbo.service.IService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.List;

@DubboService(version = "1.0.0")
@Service
public class ServiceImpl implements IService {
    @Override
    public boolean reportRequestMapping(List<MethodUriMappingBean> methodUriMappingBeans) {

        methodUriMappingBeans.forEach(System.out::println);

        return false;
    }
}
