package org.springframework.context.event;

import org.springframework.context.ApplicationContext;

/**
 * @Author ervin
 * @Date 2023/8/6
 */
public class ContextClosedEvent extends ApplicationContextEvent{

    public ContextClosedEvent(ApplicationContext source) {
        super(source);
    }
}
