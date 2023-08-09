package org.springframework.common;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @Author ervin
 * @Date 2023/8/9
 */
public class PersonServiceBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("吃饭前要先睡一觉");
    }
}
