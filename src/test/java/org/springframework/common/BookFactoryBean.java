package org.springframework.common;

import org.springframework.bean.Book;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Author ervin
 * @Date 2023/8/6
 */
public class BookFactoryBean implements FactoryBean<Book> {

    private String name;

    @Override
    public Book getObject() throws Exception {
        Book book = new Book();
        book.setName(name);
        return book;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setName(String name) {
        this.name = name;
    }
}
