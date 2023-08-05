package org.springframework.beans.factory;

import org.springframework.beans.BeansException;

/**
 * @Author ervin
 * @Date 2023/8/5
 */
public interface ObjectFactory<T> {

    T getObject() throws BeansException;
}
