package com.example.spring.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @Author ervin
 * @Date 2023/8/7
 */
public class ProxyCreator {


    /**
     * 创建动态代理对象并返回
     *
     * @param targetClass       被代理的Class对象
     * @param methodInterceptor 方法拦截器
     * @return
     */
    public static Object createProxy(Class<?> targetClass, MethodInterceptor methodInterceptor) {
        return Enhancer.create(targetClass, methodInterceptor);
    }
}
