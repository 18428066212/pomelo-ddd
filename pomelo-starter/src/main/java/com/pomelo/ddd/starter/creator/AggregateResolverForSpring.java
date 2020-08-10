package com.pomelo.ddd.starter.creator;

import com.pomelo.ddd.core.bean.Resolver;
import com.pomelo.ddd.starter.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;
import java.lang.reflect.Field;

/**
 * spring 注入属性，有
 *
 * @author 何刚
 * 有 @Autowired 或 @Resource 注解需要注入属性
 * @date 2020/8/10 12:50
 */
public class AggregateResolverForSpring implements Resolver {
    @Override
    public void resolve(Object o) {

        Class<?> aClass = o.getClass();
        Field[] fields = aClass.getDeclaredFields();

        for (Field field : fields) {
            Resource resource = field.getAnnotation(Resource.class);
            field.setAccessible(true);
            if (resource == null) {
                Autowired autowired = field.getAnnotation(Autowired.class);
                if (autowired == null) {
                    continue;
                }
                Qualifier qualifier = field.getAnnotation(Qualifier.class);
                if (qualifier == null) {

                    try {
                        field.set(o, SpringUtil.getBean(field.getType()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        throw new InternalError(e.toString(), e);
                    }
                    continue;
                }
                String value = qualifier.value();
                try {
                    Object bean = SpringUtil.getBean(value);
                    field.set(o, bean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw new InternalError(e.toString(), e);
                }
                continue;
            }

            String name = resource.name();
            try {
                Object bean = SpringUtil.getBean(name);
                field.set(o, bean);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new InternalError(e.toString(), e);
            }

        }

    }
}
