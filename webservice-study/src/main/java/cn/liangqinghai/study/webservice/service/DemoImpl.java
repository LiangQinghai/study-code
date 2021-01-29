package cn.liangqinghai.study.webservice.service;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import cn.liangqinghai.study.webservice.CetusResult;
import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.ws.ServiceMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Project name: study-code</p>
 * <p>Package name: cn.liangqinghai.study.webservice.service</p>
 * <p>File name: DemoImpl</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/1/25 10:02
 */
@WebService(endpointInterface = "cn.liangqinghai.study.webservice.service.DemoService")
@Component
public class DemoImpl implements DemoService {

    @Override
    public String hello() {

        return "hello";

    }
    @Override
    public ArrayList<Bean> beans() {

//        System.out.println(username);

        ArrayList<Bean> beans = new ArrayList<>();

        for (int i = 0; i < 20; i++) {

            Bean bean = new Bean();
            bean.setName("name_" + i);

            ArrayList<String> goods = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                goods.add("good_" + i + "_" + j);
            }
            bean.setGoods(goods);
            beans.add(bean);

        }

        return beans;

    }

    @Override
    public HashMap<String, Object> map() {

        HashMap<String, Object> res = new HashMap<>();
        res.put("success", "true");
        res.put("errorCode", "200");
        res.put("data", beans());
        return res;

    }

    @Override
    public CetusResult dto() {
        return CetusResult.success(beans());
    }

    @Override
    public CetusResult dto(String name) {
        return CetusResult.success(beans());
    }

    @XmlRootElement(name="Bean")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Bean {

        private String name;

        private ArrayList<String> goods;

        public String getName() {
            return name;
        }

        public Bean setName(String name) {
            this.name = name;
            return this;
        }

        public ArrayList<String> getGoods() {
            return goods;
        }

        public Bean setGoods(ArrayList<String> goods) {
            this.goods = goods;
            return this;
        }
    }

}
