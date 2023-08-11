package org.springframework.expanding;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.bean.Book;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author ervin
 * @Date 2023/8/10
 */
public class PropertyPlaceholderConfigurerTest {

    @DisplayName("测试bean属性配置文件占位符")
    @Test
    public void test() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:property-placeholder-configurer.xml");

        Book book = applicationContext.getBean("book", Book.class);
        Assertions.assertEquals(book.getName(), "tom and jerry");
    }
}
