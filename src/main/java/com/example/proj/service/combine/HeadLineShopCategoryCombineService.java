package com.example.proj.service.combine;

import com.example.proj.entity.dto.MainPageInfoDTO;
import com.example.proj.entity.dto.Result;

/**
 * @Author ervin
 * @Date 2023/8/1
 */
public interface HeadLineShopCategoryCombineService {

    Result<MainPageInfoDTO> getMainPageInfo();
}
