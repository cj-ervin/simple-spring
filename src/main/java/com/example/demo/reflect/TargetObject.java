package com.example.demo.reflect;

/**
 * @Author ervin
 * @Date 2023/8/1
 */
public class TargetObject {

    private String name;

    public TargetObject() {
    }

    public void publicMethod(String s) {
        System.out.println("method param is " + s);
    }

    private void privateMethod() {
        System.out.println("my name is " + name);
    }
}
