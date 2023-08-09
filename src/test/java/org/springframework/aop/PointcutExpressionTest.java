package org.springframework.aop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.service.PointcutExpressionService;

import java.lang.reflect.Method;

/**
 * @Author ervin
 * @Date 2023/8/9
 */
public class PointcutExpressionTest {

    @Test
    @DisplayName("切面表达式测试")
    public void testPointcutExpression() throws NoSuchMethodException {
        AspectJExpressionPointcut expressionPointcut =
                new AspectJExpressionPointcut("execution(* org.springframework.service.PointcutExpressionService.*(..))");

        Class<PointcutExpressionService> serviceClass = PointcutExpressionService.class;
        Method method = serviceClass.getDeclaredMethod("doSomething");

        Assertions.assertTrue(expressionPointcut.matches(serviceClass));
        Assertions.assertTrue(expressionPointcut.matches(method, serviceClass));
    }
}
