package cn.com.gome.page.utils;

import cn.com.gome.page.excp.PageException;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

public class BeanUtils {

    private static final Logger LOG = LoggerFactory.getLogger(BeanUtils.class);

    private BeanUtils() {
    }

    public static Object getPropertyValue(Object entity, String fieldName) {
        Object value;
        try {
            value = PropertyUtils.getProperty(entity, fieldName);
        } catch (Exception e) {
            throw new PageException("无法获得对象" + entity.getClass().getSimpleName() + "的属性" + fieldName + "的值",e);
        }
        return value;
    }

    public static <T extends Serializable> T newObject(Class<T> adminClass) {
        T object = null;
        try {
            object = adminClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOG.error("创建" + adminClass + "新对象失败", e);
        }
        return object;

    }

    public static void setProperty(Object object, String fieldName, Object value) {
        try {
            PropertyUtils.setProperty(object,fieldName,value);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOG.error("设置属性值出错，类" + object.getClass().getSimpleName() + ",属性名为" + fieldName + ",值为"+value, e);
        }

    }

    public static Class<?> getPropertyType(Object object, String fieldName) {
        try {
            return PropertyUtils.getPropertyType(object, fieldName);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new PageException("获取属性类型出错，类" + object.getClass().getSimpleName() + ",属性名为" + fieldName, e);
        }
    }
}
