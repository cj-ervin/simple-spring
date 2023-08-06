package org.springframework.context;

import java.util.EventListener;

/**
 * @Author ervin
 * @Date 2023/8/6
 */
public interface ApplicationListener<T extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(T event);
}
