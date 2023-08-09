package org.springframework.aop;

/**
 * @Author ervin
 * @Date 2023/8/9
 */
public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();
}
