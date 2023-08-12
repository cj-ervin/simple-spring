package org.springframework.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.bean.Book;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;

/**
 * @Author ervin
 * @Date 2023/8/11
 */
public class TypeConversionSecondPartTest {

    @DisplayName("类型转换测试")
    @Test
    public void testConversionService() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:type-conversion-second-part.xml");

        Book book = applicationContext.getBean("book", Book.class);
        Assertions.assertEquals(book.getPrice(), 30);
        Assertions.assertEquals(book.getProduceDate(), LocalDate.of(2022, 1, 1));
    }
}
