package org.springframework.common;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @Author ervin
 * @Date 2023/8/12
 */
public class ABeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println(" before advice，前置通知。。。");
    }
}
