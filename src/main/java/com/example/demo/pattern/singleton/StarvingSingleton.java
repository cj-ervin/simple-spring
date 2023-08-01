package com.example.demo.pattern.singleton;

/**
 * 饿汉单例
 *
 * @Author ervin
 * @Date 2023/8/1
 */
public class StarvingSingleton {

    private StarvingSingleton() {
    }

    private final static StarvingSingleton INSTANCE = new StarvingSingleton();

    public static StarvingSingleton getInstance() {
        return INSTANCE;
    }
}
