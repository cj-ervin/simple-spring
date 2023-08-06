package org.springframework.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.bean.Book;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author ervin
 * @Date 2023/8/6
 */
public class PrototypeBeanTest {

    @DisplayName("测试prototypeBean")
    @Test
    public void testPrototypeBean() {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:prototype-bean.xml");
        Object book1 = applicationContext.getBean("book", Book.class);
        Object book2 = applicationContext.getBean("book", Book.class);
        System.out.println(book2 == book1);
        Assertions.assertNotSame(book1, book2);
    }
}
