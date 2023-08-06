package org.springframework.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.bean.Book;
import org.springframework.bean.Student;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author ervin
 * @Date 2023/8/6
 */
public class ApplicationContextTest {

    @DisplayName("测试ApplicationContext")
    @Test
    public void testApplicationContext() {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:hello.xml");
        Student student = (Student) applicationContext.getBean("student");
        System.out.println(student);
        //student的name在CustomBeanFactoryPostProcessor中被修改成 curry
        Assertions.assertEquals(student.getName(), "curry");

        Book book = (Book) applicationContext.getBean("book");
        System.out.println(book);
        //book的name在CustomerBeanPostProcessor中被修改成红楼梦
        Assertions.assertEquals(book.getName(), "红楼梦");
    }
}
