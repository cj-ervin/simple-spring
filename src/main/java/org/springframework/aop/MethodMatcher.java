package org.springframework.aop;

import java.lang.reflect.Method;

/**
 * @Author ervin
 * @Date 2023/8/9
 */
public interface MethodMatcher {

    boolean matches(Method method, Class<?> targetClass);
}
