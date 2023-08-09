package org.springframework.aop;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;

/**
 * @Author ervin
 * @Date 2023/8/9
 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
