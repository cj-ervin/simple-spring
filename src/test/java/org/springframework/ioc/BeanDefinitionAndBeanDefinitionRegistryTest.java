package org.springframework.ioc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.service.HelloService;

/**
 * @Author ervin
 * @Date 2023/8/5
 */
public class BeanDefinitionAndBeanDefinitionRegistryTest {

    @DisplayName("测试BeanDefinitionAnd和DefinitionRegistry")
    @Test
    public void testBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class);

        beanFactory.registerBeanDefinition("helloService", beanDefinition);
        HelloService helloService =  (HelloService)beanFactory.getBean("helloService");
        helloService.hello();
    }
}
