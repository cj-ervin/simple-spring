package org.springframework.ioc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author ervin
 * @Date 2023/8/6
 */
public class InitAndDestroyMethodTest {

    @DisplayName("测试初始化和销毁方法")
    @Test
    public void testInitAndDestroy() {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:init-and-destroy-method.xml");
        applicationContext.registerShutdownHook();
        //或者手动关闭
//        applicationContext.close();
    }
}
