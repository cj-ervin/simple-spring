package com.example.demo.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author ervin
 * @Date 2023/7/29
 */
public class AnnotationParser {

    /**
     * 解析类上的注解
     *
     */
    public static void parseTypeAnnotation() throws Exception {
        Class clazz = Class.forName("com.example.demo.annotation.Book");
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            BookInfoAnnotation bookInfoAnnotation = (BookInfoAnnotation) annotation;
            System.out.println("书籍名称：" + bookInfoAnnotation.name() + "\n" +
                    "书籍标签" + bookInfoAnnotation.tag() + "\n" +
                    "书籍简介：" + bookInfoAnnotation.introduction() + "\n" +
                    "书籍序号" + bookInfoAnnotation.index());
        }
    }

    /**
     * 解析类变量的注解
     *
     */
    public static void parseFieldAnnotation() throws Exception {
        Class clazz = Class.forName("com.example.demo.annotation.Book");
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            boolean present = field.isAnnotationPresent(AuthorAnnotation.class);
            if (present) {
                AuthorAnnotation authorAnnotation = field.getAnnotation(AuthorAnnotation.class);
                System.out.println("作者名字：" + authorAnnotation.name() + "\n" +
                        "作者性别：" + authorAnnotation.gender() + "\n" +
                        "作者年龄：" + authorAnnotation.age());

                String[] languages = authorAnnotation.language();
                for (String language : languages) {
                    System.out.println("熟悉的语言： " + language);
                }
            }
        }
    }

    /**
     * 解析方法上的注解
     */
    public static void parseMethodAnnotation() throws Exception {
        Class clazz = Class.forName("com.example.demo.annotation.Book");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            boolean present = method.isAnnotationPresent(BookInfoAnnotation.class);
            if (present) {
                BookInfoAnnotation bookInfoAnnotation = method.getAnnotation(BookInfoAnnotation.class);
                System.out.println("书籍名称：" + bookInfoAnnotation.name() + "\n" +
                        "书籍标签" + bookInfoAnnotation.tag() + "\n" +
                        "书籍简介：" + bookInfoAnnotation.introduction() + "\n" +
                        "书籍序号" + bookInfoAnnotation.index());
            }

        }
    }

    public static void main(String[] args) throws Exception {
//        parseTypeAnnotation();
//        parseFieldAnnotation();
        parseMethodAnnotation();
    }

}
