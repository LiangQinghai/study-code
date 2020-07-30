package cn.liangqinghai.study.mbp.utils.converter;

import com.alibaba.fastjson.JSONObject;
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

        Map<String, Object> map = BeanConverter.beanToMap(new TestCaseBean().setDesc("Hello"));

        System.out.println(JSONObject.toJSON(map).toString());

    }

    private static class TestCaseBean {
        private String desc;

        public String getDesc() {
            return desc;
        }

        public TestCaseBean setDesc(String desc) {
            this.desc = desc;
            return this;
        }
    }

}
