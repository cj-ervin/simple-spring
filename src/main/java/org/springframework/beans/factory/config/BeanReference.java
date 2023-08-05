package org.springframework.beans.factory.config;

/**
 * 一个bean对另一个bean的引用
 *
 * @Author ervin
 * @Date 2023/8/5
 */
public class BeanReference {

    private final String beanName;


    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
