package org.springframework.aop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.service.PersonService;

/**
 * @Author ervin
 * @Date 2023/8/10
 */
public class AutoProxyTest {

    @DisplayName("测试自动代理")
    @Test
    public void testAutoProxy() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:auto-proxy.xml");
        PersonService personService = (PersonService) applicationContext.getBean("personService", PersonService.class);
        personService.eat();
    }
}
