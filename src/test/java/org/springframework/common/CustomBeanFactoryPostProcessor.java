package org.springframework.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @Author ervin
 * @Date 2023/8/5
 */
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("自定义BeanFactoryPostProcessor");
        BeanDefinition student = beanFactory.getBeanDefinition("student");
        PropertyValues studentPropertyValues = student.getPropertyValues();
        //修改student的name
        studentPropertyValues.addPropertyValue(new PropertyValue("name", "curry"));
    }
}
