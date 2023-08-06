package org.springframework.context;

/**
 * 事件发布者接口
 *
 * @Author ervin
 * @Date 2023/8/6
 */
public interface ApplicationEventPublisher {

    /**
     * 发布事件
     *
     * @param event
     */
    void publishEvent(ApplicationEvent event);
}
