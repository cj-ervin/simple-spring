package org.springframework.aop;

import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import org.springframework.common.PersonServiceBeforeAdvice;
import org.springframework.service.PersonService;
import org.springframework.service.PersonServiceImpl;

/**
 * @Author ervin
 * @Date 2023/8/9
 */
public class AdvicorTest {

    @Test
    public void testAdvisor() {
        PersonService service = new PersonServiceImpl();
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        String expression = "execution(* org.springframework.service.PersonService.eat(..))";
        MethodBeforeAdviceInterceptor methodInterceptor = new MethodBeforeAdviceInterceptor(new PersonServiceBeforeAdvice());

        advisor.setExpression(expression);
        advisor.setAdvice(methodInterceptor);

        ClassFilter classFilter = advisor.getPointcut().getClassFilter();
        if (classFilter.matches(service.getClass())) {
            ProxyFactory proxyFactory = new ProxyFactory();
            TargetSource targetSource = new TargetSource(service);
            proxyFactory.setTargetSource(targetSource);
            proxyFactory.addAdvisor(advisor);

            PersonService proxy = (PersonService) proxyFactory.getProxy();
            proxy.eat();
        }
    }
}
