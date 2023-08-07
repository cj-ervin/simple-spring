package com.example.spring.aop;

import com.example.proj.controller.frontend.MainPageController;
import com.example.proj.entity.dto.MainPageInfoDTO;
import com.example.proj.entity.dto.Result;
import com.example.spring.core.BeanContainer;
import com.example.spring.inject.DependencyInjector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @Author ervin
 * @Date 2023/8/7
 */
public class AspectWeaverTest {

    @DisplayName("测试aop")
    @Test
    public void testAop() {

        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("com.example.proj");
        new AspectWeaver().doAop();
        new DependencyInjector().doIoc();
        MainPageController mainPageController = (MainPageController)beanContainer.getBean(MainPageController.class);
        Result<MainPageInfoDTO> mainPageInfo = mainPageController.getMainPageInfo(null, null);
        System.out.println("===========");
    }
}
