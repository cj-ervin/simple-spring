package com.example.spring.inject;

import com.example.spring.core.BeanContainer;
import com.example.spring.inject.annotation.Autowired;
import com.example.spring.util.ClassUtil;
import com.example.spring.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * @Author ervin
 * @Date 2023/8/1
 */
@Slf4j
public class DependencyInjector {
    private BeanContainer beanContainer;

    public DependencyInjector() {
        beanContainer = BeanContainer.getInstance();
    }

    /**
     * 依赖注入
     */
    public void doIoc() {
        //1.遍历Bean容器中所有的Class,对象
        Set<Class<?>> classSet = beanContainer.getClasses();
        if (ValidationUtil.isEmpty(classSet)) {
            log.warn("empty class set in bean container");
            return;
        }
        for (Class<?> clazz : classSet) {
            //2.遍历C1ass对象的所有成员变量
            Field[] fields = clazz.getDeclaredFields();
            if (ValidationUtil.isEmpty(fields)) {
                continue;
            }
            for (Field field : fields) {
                //3.找出被Autowired标记的成员变量
                if (field.isAnnotationPresent(Autowired.class)) {
                    Autowired fieldAnnotation = field.getAnnotation(Autowired.class);
                    String autowiredValue = fieldAnnotation.value();
                    // 4.获取这些成员变量的类型
                    Class<?> fieldClassType = field.getType();
                    // 5.获取这些成员变量的类型在容器里对应的实例
                    Object fieldObject = getFieldInstance(fieldClassType, autowiredValue);
                    if (fieldObject != null) {
                        // 6.通过反射将对应的成员变量实例注入到成员变量所在类的实例里
                        Object beanObject = beanContainer.getBean(clazz);
                        ClassUtil.setField(field, beanObject, fieldObject, true);
                    } else {
                        throw new RuntimeException("unable to inject relevant type，target fieldClass is:" + fieldClassType.getName() + " autowiredValue is : " + autowiredValue);
                    }
                }
            }
        }
    }

    /**
     * 获取字段对象示例
     *
     * @param fieldClass     字段 class 对象
     * @param autowiredValue autowired 注解 value 值
     * @return 字段对象示例
     */
    private Object getFieldInstance(Class<?> fieldClass, String autowiredValue) {
        Object fieldObject = beanContainer.getBean(fieldClass);
        if (fieldObject != null) {
            return fieldClass;
        } else {
            Class<?> implementClass = getImplementClass(fieldClass, autowiredValue);
            if (implementClass != null) {
                return beanContainer.getBean(implementClass);
            } else {
                return null;
            }
        }
    }

    private Class<?> getImplementClass(Class<?> fieldClass, String autowiredValue) {
        Set<Class<?>> classesBySuper = beanContainer.getClassesBySuper(fieldClass);
        if (ValidationUtil.isEmpty(classesBySuper)) {
            return null;
        }
        if (ValidationUtil.isEmpty(autowiredValue)) {
            if (classesBySuper.size() == 1) {
                return classesBySuper.iterator().next();
            } else {
                //如果多于两个实现类且用户未指定其中一个实现类，则抛出异常
                throw new RuntimeException("multiple implemented classes for " + fieldClass.getName() + " please set @Autowired's value to pick one");
            }
        } else {
            for (Class<?> c : classesBySuper) {
                if (autowiredValue.equals(c.getSimpleName())) {
                    return c;
                }
            }
            return null;
        }
    }
}
