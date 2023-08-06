package org.springframework.ioc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.common.event.CustomEvent;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author ervin
 * @Date 2023/8/6
 */
public class EventAndEventListenerTest {

    @DisplayName("测试EventAndEventListener")
    @Test
    public void testEventAndEventListener() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:event-and-event-listener.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext));

//        applicationContext.registerShutdownHook();

    }
}
