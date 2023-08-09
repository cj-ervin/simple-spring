package org.springframework.service;

/**
 * @Author ervin
 * @Date 2023/8/9
 */
public class PersonServiceImpl implements PersonService{

    @Override
    public void eat() {
        System.out.println("八戒正在偷吃");
    }
}
