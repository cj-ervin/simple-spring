package org.springframework.context.event;

import org.springframework.context.ApplicationContext;

/**
 * @Author ervin
 * @Date 2023/8/6
 */
public class ContextRefreshedEvent extends ApplicationContextEvent{

    public ContextRefreshedEvent(ApplicationContext source) {
        super(source);
    }
}
