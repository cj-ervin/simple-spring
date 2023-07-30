package com.example.demo.annotation;

/**
 * @author ervin
 * @Date 2023/7/29
 */
@BookInfoAnnotation(name = "如何编程", tag = "编程", introduction = "一本关于编程的书")
public class Book {

    @AuthorAnnotation(name = "dnmy", age = 30, language = {"Java", "Python", "C"})
    private String author;

    @BookInfoAnnotation(name = "如何摸鱼", tag = "摸鱼", introduction = "一本关于摸鱼的书")
    public void getBookInfo() {

    }
}
