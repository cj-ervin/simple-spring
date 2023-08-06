package org.springframework.bean;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Author ervin
 * @Date 2023/8/5
 */
@Data
public class Student implements InitializingBean, DisposableBean {

    private String name;

    private int age;

    private Book book;

    public void customInitMethod() {
        System.out.println("自定义初始化方法1：xml init-method 配置的 customInitMethod方法");
    }

    public void customDestroyMethod() {
        System.out.println("自定义销毁方法1：xml destroy-method 配置的 customDestroyMethod方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("自定义销毁方法2：通过 InitializingBean#destroy 销毁");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("自定义初始化方法2：通过 InitializingBean#afterPropertiesSet 初始化");
    }
}
