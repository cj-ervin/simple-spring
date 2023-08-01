package com.example.spring.inject;

import com.example.proj.controller.frontend.MainPageController;
import com.example.proj.service.combine.impl.HeadLineShopCategoryCombineServiceImpl;
import com.example.proj.service.combine.impl.HeadLineShopCategoryCombineServiceImpl2;
import com.example.spring.core.BeanContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @Author ervin
 * @Date 2023/8/1
 */
public class DependencyInjectorTest {

    @DisplayName("依赖注入 doIoc")
    @Test
    public void doIocTest() {

        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("com.example.proj");
        Assertions.assertTrue(beanContainer.isLoaded());

        MainPageController mainPageController = (MainPageController) beanContainer.getBean(MainPageController.class);
        Assertions.assertTrue(mainPageController instanceof MainPageController);
//        Assertions.assertNull(mainPageController.getHeadLineShopCategoryCombineService());

        new DependencyInjector().doIoc();
        Assertions.assertNotEquals(null, mainPageController.getHeadLineShopCategoryCombineService());
        Assertions.assertTrue(mainPageController.getHeadLineShopCategoryCombineService() instanceof HeadLineShopCategoryCombineServiceImpl);
        Assertions.assertFalse(mainPageController.getHeadLineShopCategoryCombineService() instanceof HeadLineShopCategoryCombineServiceImpl2);
    }
}
