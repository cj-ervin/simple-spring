package org.springframework.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.bean.A;
import org.springframework.bean.B;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author ervin
 * @Date 2023/8/12
 */
public class CircularReferenceWithoutProxyBeanTest {

    @DisplayName("测试循环依赖(无代理对象)")
    @Test
    public void tesCircularReferenceWithoutProxyBean() {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:circular-reference-without-proxy-bean.xml");
        A a = applicationContext.getBean("a", A.class);
        B b = applicationContext.getBean("b", B.class);
        Assertions.assertSame(a.getB(), b);
    }
}
