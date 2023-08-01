package com.example.demo.pattern.singleton;

/**
 * 双重锁校验 单例
 *
 * @Author ervin
 * @Date 2023/8/1
 */
public class LazyDoubleCheckSingleton {

    private LazyDoubleCheckSingleton() {
    }

    private volatile static LazyDoubleCheckSingleton instance;

    public static LazyDoubleCheckSingleton getInstance() {

        if (instance == null) {
            synchronized (LazyDoubleCheckSingleton.class) {
                if (instance == null) {
                    //memory = allocate(); //1.分配对象内存空间
                    //instance(memory);    //2.初始化对象
                    //instance = memory;   //3.设置instance指向刚分配的内存地址，此时instance！=null
                    instance = new LazyDoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
