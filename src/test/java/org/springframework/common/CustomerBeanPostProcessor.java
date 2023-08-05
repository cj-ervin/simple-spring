package org.springframework.common;

import org.springframework.bean.Book;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @Author ervin
 * @Date 2023/8/5
 */
public class CustomerBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("自定义beanPostProcessor前置方法，beanName: " + beanName);
        if ("book".equals(beanName)) {
            ((Book) bean).setName("红楼梦");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("自定义beanPostProcessor后置方法，beanName: " + beanName);
        return null;
    }
}
