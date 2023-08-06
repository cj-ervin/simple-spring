package org.springframework.common.event;

import org.springframework.context.ApplicationListener;

/**
 * @Author ervin
 * @Date 2023/8/6
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println(this.getClass().getName());
    }
}
