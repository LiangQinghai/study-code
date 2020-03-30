package cn.liangqinghai.study.web.rest.conf;

import org.apache.commons.beanutils.MethodUtils;
import org.dozer.CustomConverter;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author LiangQinghai
 * @Title BeanMapperEnumConverter
 * @ProjectName study-code
 * @Description
 * @date 3/30/2020 3:40 PM
 */
public class BeanMapperEnumConverter implements CustomConverter {

    private static final ConcurrentHashMap<Class<?>, Enum<?>[]> ENUM_CACHE = new ConcurrentHashMap<>();

    private static Enum<?>[] getEnums(Class<?> clazz) {

        Enum<?>[] enumInCache = ENUM_CACHE.get(clazz);

        if (enumInCache == null) {
            try {
                ENUM_CACHE.putIfAbsent(clazz, enumInCache = (Enum<?>[]) MethodUtils.invokeStaticMethod(clazz, "values", null));
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return enumInCache;

    }

    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {

        if (sourceFieldValue instanceof Enum && (destinationClass.isAssignableFrom(Integer.class) || destinationClass.isAssignableFrom(int.class))) {
            return ((Enum<?>) sourceFieldValue).ordinal();
        } else if (sourceFieldValue instanceof Integer && Enum.class.isAssignableFrom(destinationClass)) {
            return getEnums(destinationClass)[(Integer) sourceFieldValue];
        }

        return null;
    }
}
