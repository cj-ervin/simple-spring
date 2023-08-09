package org.springframework.aop.framework.adapter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterAdvice;
import org.springframework.aop.AfterReturningAdvice;

/**
 * @Author ervin
 * @Date 2023/8/9
 */
public class AfterReturningAdviceInterceptor implements MethodInterceptor, AfterAdvice {
    private AfterReturningAdvice advice;

    public AfterReturningAdviceInterceptor() {
    }

    public AfterReturningAdviceInterceptor(AfterReturningAdvice advice) {
        this.advice = advice;
    }

    public void setAdvice(AfterReturningAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object retVal = methodInvocation.proceed();
        this.advice.afterReturning(retVal,
                methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        return retVal;
    }
}
