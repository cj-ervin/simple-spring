package org.springframework.bean;

import lombok.Data;

/**
 * @Author ervin
 * @Date 2023/8/12
 */
@Data
public class A {

    private String info;

    private B b;

    public void func(){
        System.out.println("A的func方法执行。。。");
    }
}
