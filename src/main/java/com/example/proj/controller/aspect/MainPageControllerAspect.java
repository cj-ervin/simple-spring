package com.example.proj.controller.aspect;

import com.example.spring.aop.annotation.Aspect;
import com.example.spring.aop.annotation.Order;
import com.example.spring.aop.aspect.DefaultAspect;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @Author ervin
 * @Date 2023/8/7
 */
@Order(value = 1)
@Slf4j
@Aspect(pointcut = "execution(* com.example.proj.controller.frontend.MainPageController.*(..))")
public class MainPageControllerAspect extends DefaultAspect {

    @Override
    public void before(Class<?> targetClass, Method method, Object[] args) throws Throwable {
        System.out.println("切面类 MainPageControllerAspect 执行前置方法");
        log.info("切面类 MainPageControllerAspect 执行前置方法");
    }

    @Override
    public Object afterReturning(Class<?> targetClass, Method method, Object[] args, Object returnValue) throws Throwable {
        return super.afterReturning(targetClass, method, args, returnValue);
    }

    @Override
    public void afterThrowing(Class<?> targetClass, Method method, Object[] args, Throwable e) throws Throwable {
        super.afterThrowing(targetClass, method, args, e);
    }
}
