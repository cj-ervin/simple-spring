package org.springframework.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.bean.Book;
import org.springframework.bean.Student;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author ervin
 * @Date 2023/8/10
 */
public class AutowiredAnnotationTest {

    @DisplayName("测试@Autowired注解")
    @Test
    public void testAutowiredAnnotation() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:autowired-annotation.xml");
        Student student = applicationContext.getBean("student", Student.class);
        Book book = student.getBook();
        Assertions.assertNotNull(book);
    }
}
