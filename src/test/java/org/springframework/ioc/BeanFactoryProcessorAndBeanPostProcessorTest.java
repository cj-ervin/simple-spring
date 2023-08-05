package org.springframework.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.bean.Book;
import org.springframework.bean.Student;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.common.CustomBeanFactoryPostProcessor;
import org.springframework.common.CustomerBeanPostProcessor;

/**
 * @Author ervin
 * @Date 2023/8/5
 */
public class BeanFactoryProcessorAndBeanPostProcessorTest {


    @DisplayName("测试BeanPostFactoryPostProcessor")
    @Test
    public void testBeanPostFactoryPostProcessor() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:hello.xml");

        //beanDefinition 加载完成之后， bean 实例化之前， 修改 beanDefinition 的属性值
        CustomBeanFactoryPostProcessor customBeanFactoryPostProcessor = new CustomBeanFactoryPostProcessor();
        customBeanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        Student student = (Student) beanFactory.getBean("student");
        System.out.println(student);
        Assertions.assertEquals("curry", student.getName());
    }

    @DisplayName("测试beanPostProcessor")
    @Test
    public void testBeanPostProcessor() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:hello.xml");

        CustomerBeanPostProcessor beanPostProcessor = new CustomerBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        Book book = (Book) beanFactory.getBean("book");
        System.out.println(book);
        Assertions.assertEquals("红楼梦", book.getName());
    }
}
