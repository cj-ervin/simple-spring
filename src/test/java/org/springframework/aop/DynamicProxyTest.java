package org.springframework.aop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.framework.CglibAopProxy;
import org.springframework.aop.framework.JdkDynamicAopProxy;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import org.springframework.common.PersonServiceBeforeAdvice;
import org.springframework.service.PersonService;
import org.springframework.service.PersonServiceImpl;

/**
 * @Author ervin
 * @Date 2023/8/9
 */
public class DynamicProxyTest {

    private AdvisedSupport advisedSupport;

    @BeforeEach
    public void setup() {
        PersonService personService = new PersonServiceImpl();
        advisedSupport = new ProxyFactory();
        String expression = "execution(* org.springframework.service.PersonService.eat(..))";
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression(expression);

        MethodBeforeAdviceInterceptor methodInterceptor = new MethodBeforeAdviceInterceptor(new PersonServiceBeforeAdvice());
        advisor.setAdvice(methodInterceptor);

        TargetSource targetSource = new TargetSource(personService);

        advisedSupport.setTargetSource(targetSource);
        advisedSupport.addAdvisor(advisor);
    }

    @Test
    public void testJdkDynamicProxy() throws Exception {
        PersonService proxy = (PersonService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy.eat();
    }

    @Test
    public void testCglibDynamicProxy() throws Exception {
        PersonService proxy = (PersonService) new CglibAopProxy(advisedSupport).getProxy();
        proxy.eat();
    }

    @Test
    public void testProxyFactory() throws Exception {
        // 使用JDK动态代理
        ProxyFactory factory = (ProxyFactory) advisedSupport;
        factory.setProxyTargetClass(false);
        PersonService proxy = (PersonService) factory.getProxy();
        proxy.eat();

        // 使用CGLIB动态代理
        factory.setProxyTargetClass(true);
        proxy = (PersonService) factory.getProxy();
        proxy.eat();
    }

    public void testBeforeAdvice() throws Exception {
        ProxyFactory factory = (ProxyFactory) advisedSupport;
        PersonService proxy = (PersonService) factory.getProxy();
        proxy.eat();
    }
}
