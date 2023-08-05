package org.springframework.beans.factory;

/**
 * @Author ervin
 * @Date 2023/8/5
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    boolean isSingleton();
}
