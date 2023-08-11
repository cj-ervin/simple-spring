package org.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @Author ervin
 * @Date 2023/8/10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Inherited
@Documented
public @interface Qualifier {

    String value() default "";

}
