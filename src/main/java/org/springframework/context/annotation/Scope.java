package org.springframework.context.annotation;

import java.lang.annotation.*;

/**
 * @Author ervin
 * @Date 2023/8/10
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {

    String value() default "singleton";
}

