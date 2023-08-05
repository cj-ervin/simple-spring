package org.springframework.beans.factory.config;

/**
 * 单例注册
 *
 * @Author ervin
 * @Date 2023/8/5
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void addSingleton(String beanName, Object singletonObject);
}
