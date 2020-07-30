package cn.liangqinghai.study.mbp.utils.converter;

import cn.liangqinghai.study.mbp.utils.modelmapper.date.DateModule;
import cn.liangqinghai.study.mbp.utils.modelmapper.date.DateModuleConfig;
import cn.liangqinghai.study.mbp.utils.modelmapper.json.JsonModule;
import cn.liangqinghai.study.mbp.utils.modelmapper.opt.OptionalModule;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.cglib.beans.BeanMap;

import java.time.ZoneOffset;
import java.util.*;

/**
 * @author LiangQinghai
 * @Title BeanConverter
 * @ProjectName study-code
 * @Description
 * @date 2020/5/20 19:10
 */
public class BeanConverter {

    private static final ModelMapper MAPPER;

    static {

        MAPPER = new ModelMapper();

        DateModuleConfig moduleConfig = new DateModuleConfig().setZoneId(ZoneOffset.UTC);

        MAPPER.registerModule(new DateModule(moduleConfig))
                .registerModule(new JsonModule())
                .registerModule(new OptionalModule());

        MAPPER.getConfiguration().setFullTypeMatchingRequired(true)
                .setMatchingStrategy(MatchingStrategies.STRICT);

    }

    public static ModelMapper getMapper() {
        return MAPPER;
    }

    /**
     * bean to map
     *
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {

        Map<String, Object> map = Collections.emptyMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            map = new HashMap<>(beanMap.keySet().size());
            for (Object o : beanMap.keySet()) {
                map.put(String.valueOf(o), beanMap.get(o));
            }
        }

        return map;

    }


    /**
     * List to List<Map>
     *
     * @param objList
     * @param <T>
     * @return
     */
    public static <T> List<Map<String, Object>> beansToMap(List<T> objList) {

        List<Map<String, Object>> list = Collections.emptyList();

        if (CollectionUtils.isNotEmpty(objList)) {
            list = new ArrayList<>();
            Map<String, Object> map;
            T bean;
            for (T t : objList) {
                bean = t;
                map = beanToMap(bean);
                list.add(map);
            }
        }

        return list;

    }

}
