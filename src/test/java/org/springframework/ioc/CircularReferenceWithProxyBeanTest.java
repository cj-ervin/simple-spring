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
public class CircularReferenceWithProxyBeanTest {

    @DisplayName("测试循环依赖(有代理对象)")
    @Test
    public void tesCircularReferenceWithoutProxyBean() {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:circular-reference-with-proxy-bean.xml");
        A a = applicationContext.getBean("a", A.class);
        B b = applicationContext.getBean("b", B.class);
        //说明注入到 b 中的对象 a 是生成的代理对象
        Assertions.assertSame(b.getA(), a);
        a.func();
        System.out.println("======================");
        b.getA().func();
        System.out.println("#########################");
        System.out.println("a.getInfo() :" + a.getInfo());
    }
}
