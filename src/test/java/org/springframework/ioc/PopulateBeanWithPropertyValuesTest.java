package org.springframework.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.bean.Book;
import org.springframework.bean.Student;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanReference;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @Author ervin
 * @Date 2023/8/5
 */
public class PopulateBeanWithPropertyValuesTest {

    @DisplayName("bean属性注入")
    @Test
    public void testPopulateBeanWithPropertyValues() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name","jack"));
        propertyValues.addPropertyValue(new PropertyValue("age",18));
        BeanDefinition beanDefinition = new BeanDefinition(Student.class, propertyValues);

        beanFactory.registerBeanDefinition("student", beanDefinition);
        Student student = (Student) beanFactory.getBean("student");
        System.out.println("student: " + student);
        Assertions.assertEquals("jack", student.getName());
        Assertions.assertEquals(18, student.getAge());
    }


    @DisplayName("为bean注入bean")
    @Test
    public void testPopulateBeanWithBean() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //注册book实例
        PropertyValues propertyValuesForBook = new PropertyValues();
        propertyValuesForBook.addPropertyValue(new PropertyValue("name", "鲁滨逊漂流记"));
        propertyValuesForBook.addPropertyValue(new PropertyValue("tag", "文学"));
        BeanDefinition bookBeanDefinition = new BeanDefinition(Book.class, propertyValuesForBook);
        beanFactory.registerBeanDefinition("book", bookBeanDefinition);

        //注册Student实例
        PropertyValues propertyValuesForStudent = new PropertyValues();
        propertyValuesForStudent.addPropertyValue(new PropertyValue("name", "jim"));
        propertyValuesForStudent.addPropertyValue(new PropertyValue("age", 18));
        //Student实例依赖book实例
        propertyValuesForStudent.addPropertyValue(new PropertyValue("book", new BeanReference("book")));
        BeanDefinition beanDefinition = new BeanDefinition(Student.class, propertyValuesForStudent);
        beanFactory.registerBeanDefinition("student", beanDefinition);

        Student student = (Student) beanFactory.getBean("student");
        System.out.println(student);
        Assertions.assertEquals(student.getName(),"jim");
        Assertions.assertEquals(student.getAge(),18);
        Book book = student.getBook();
        Assertions.assertNotNull(book);
        Assertions.assertEquals(book.getName(), "鲁滨逊漂流记");
    }
}
