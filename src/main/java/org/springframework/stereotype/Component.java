package org.springframework.stereotype;

import java.lang.annotation.*;

/**
 * @Author ervin
 * @Date 2023/8/10
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";
}