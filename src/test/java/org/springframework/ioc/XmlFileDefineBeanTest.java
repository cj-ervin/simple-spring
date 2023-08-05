package org.springframework.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.bean.Book;
import org.springframework.bean.Student;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @Author ervin
 * @Date 2023/8/5
 */
public class XmlFileDefineBeanTest {


    @DisplayName("测试beanDefinitionReader")
    @Test
    public void testXmlFile() {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:hello.xml");

        Student student = (Student) beanFactory.getBean("student");
        System.out.println(student);
        Assertions.assertEquals(student.getName(), "tom");
        Assertions.assertEquals(student.getBook().getName(),"西游记");

        Book book = (Book) beanFactory.getBean("book");
        System.out.println(book);
        Assertions.assertEquals(book.getName(), "西游记");
    }
}
