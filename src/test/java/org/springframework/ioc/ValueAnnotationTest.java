package org.springframework.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.bean.Book;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author ervin
 * @Date 2023/8/10
 */
public class ValueAnnotationTest {

    @DisplayName("测试@value注解")
    @Test
    public void testValueAnnotation() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:value-annotation.xml");
        Book book = applicationContext.getBean("book", Book.class);
        Assertions.assertNotNull(book);
        Assertions.assertEquals("tom and jerry", book.getName());
    }
}
