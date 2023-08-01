package com.example.demo.pattern.singleton;

/**
 * 懒汉单例
 *
 * @Author ervin
 * @Date 2023/8/1
 */
public class LazySingleton {

    private LazySingleton() {
    }

    private static LazySingleton instance;

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
