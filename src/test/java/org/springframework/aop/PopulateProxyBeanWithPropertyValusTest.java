package org.springframework.aop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.service.DogService;

/**
 * @Author ervin
 * @Date 2023/8/11
 */
public class PopulateProxyBeanWithPropertyValusTest {

    @DisplayName("为代理类设置属性")
    @Test
    public void testPopulateProxyBeanWithPropertyValue() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:populate-proxy-bean-with-property-values.xml");
        DogService service = applicationContext.getBean("dogService", DogService.class);
        service.eat();
    }
}
