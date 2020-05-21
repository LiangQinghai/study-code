package cn.liangqinghai.study.mbp.utils.converter;

import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;
import org.junit.Test;

import java.util.Map;

/**
 * @author LiangQinghai
 * @Title BeanConverterTest
 * @ProjectName study-code
 * @Description
 * @date 2020/5/21 20:51
 */
public class BeanConverterTest {

    @Test
    public void testBeanToMap() {

        Map<String, Object> map = BeanConverter.beanToMap(TestCaseBean.builder().desc("Hello"));

        System.out.println(JSONObject.toJSON(map).toString());

    }

    @Data
    @Builder
    private static class TestCaseBean {
        private String desc;
    }

}
