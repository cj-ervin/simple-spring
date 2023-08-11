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
public class PackageScanTest {

    @DisplayName("测试包扫描")
    @Test
    public void testPackageScan() throws Exception{
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:package-scan.xml");
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:package-scan.xml");


        Book book = applicationContext.getBean("book", Book.class);
        Assertions.assertNotNull(book);
    }
}
