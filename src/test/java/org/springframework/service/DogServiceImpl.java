package org.springframework.service;

import lombok.Data;

/**
 * @Author ervin
 * @Date 2023/8/11
 */
@Data
public class DogServiceImpl implements DogService{

    private String name;
    @Override
    public void eat() {
        System.out.println("小狗正在吃狗粮, 小狗的名字叫做" + name);
    }
}
