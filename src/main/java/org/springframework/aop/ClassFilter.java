package org.springframework.aop;

/**
 * @Author ervin
 * @Date 2023/8/9
 */
public interface ClassFilter {


    boolean matches(Class<?> clazz);
}
