package org.springframework.aop;

import java.lang.reflect.Method;

/**
 * @Author ervin
 * @Date 2023/8/9
 */
public interface MethodBeforeAdvice extends BeforeAdvice{

    void before(Method method, Object[] args, Object target) throws Throwable;
}
