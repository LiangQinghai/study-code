package cn.liangqinghai.study.mbp.common.mbp;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.AbstractSqlInjector;
import com.baomidou.mybatisplus.core.injector.methods.*;
import com.baomidou.mybatisplus.extension.injector.methods.AlwaysUpdateSomeColumnById;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author LiangQinghai
 * @Title MbpSqlInjector
 * @ProjectName study-code
 * @Description
 * @date 2020/5/26 20:43
 */
public class MbpSqlInjector extends AbstractSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        return Stream.of(new Insert(),
                new Delete(),
                new DeleteById(),
                new Update(),
                new UpdateById(),
                new AlwaysUpdateSomeColumnById(),
                new SelectById(),
                new SelectCount(),
                new SelectCount(),
                new SelectObjs(),
                new SelectList())
                .collect(Collectors.toList());
    }
}
