package org.springframework.aop;

import java.lang.reflect.Method;

/**
 * @Author ervin
 * @Date 2023/8/9
 */
public interface AfterReturningAdvice extends AfterAdvice{

    void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable;
}
