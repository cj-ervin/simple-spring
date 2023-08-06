package org.springframework.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.service.HelloService;

/**
 * @Author ervin
 * @Date 2023/8/6
 */
public class AwareInterfaceTest {

    @DisplayName("测试aware接口")
    @Test
    public void testAware() {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:hello.xml");
        HelloService helloService = applicationContext.getBean(HelloService.class);
        Assertions.assertNotNull(helloService.getApplicationContext());
        Assertions.assertNotNull(helloService.getBeanFactory());
    }
}
