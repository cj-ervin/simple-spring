package com.example.proj.controller.frontend;

import com.example.proj.entity.dto.MainPageInfoDTO;
import com.example.proj.entity.dto.Result;
import com.example.proj.service.combine.HeadLineShopCategoryCombineService;
import com.example.spring.core.annotation.Controller;
import com.example.spring.inject.annotation.Autowired;
import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author ervin
 * @Date 2023/8/1
 */

@Getter
@Controller
public class MainPageController {

    //@Autowired
    @Autowired(value = "HeadLineShopCategoryCombineServiceImpl")
    private HeadLineShopCategoryCombineService headLineShopCategoryCombineService;

    public Result<MainPageInfoDTO> getMainPageInfo(HttpServletRequest req, HttpServletResponse resp) {
        return headLineShopCategoryCombineService.getMainPageInfo();
    }
}
