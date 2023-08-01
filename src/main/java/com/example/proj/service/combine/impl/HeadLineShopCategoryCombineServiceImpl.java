package com.example.proj.service.combine.impl;

import com.example.proj.entity.bo.HeadLine;
import com.example.proj.entity.bo.ShopCategory;
import com.example.proj.entity.dto.MainPageInfoDTO;
import com.example.proj.entity.dto.Result;
import com.example.proj.service.combine.HeadLineShopCategoryCombineService;
import com.example.proj.service.solo.HeadLineService;
import com.example.proj.service.solo.ShopCategoryService;
import com.example.spring.core.annotation.Service;
import com.example.spring.inject.annotation.Autowired;

import java.util.List;

/**
 * @Author ervin
 * @Date 2023/8/1
 */
@Service
public class HeadLineShopCategoryCombineServiceImpl implements HeadLineShopCategoryCombineService {
    @Autowired
    private HeadLineService headLineService;
    @Autowired
    private ShopCategoryService shopCategoryService;

    @Override
    public Result<MainPageInfoDTO> getMainPageInfo() {
        //1.获取头条列表
        HeadLine headLineCondition = new HeadLine();
        headLineCondition.setEnableStatus(1);
        Result<List<HeadLine>> HeadLineResult = headLineService.queryHeadLine(headLineCondition, 1, 4);
        //2.获取店铺类别列表
        ShopCategory shopCategoryCondition = new ShopCategory();
        Result<List<ShopCategory>> shopCategoryResult = shopCategoryService.queryShopCategory(shopCategoryCondition, 1, 100);
        //3.合并两者并返回
        Result<MainPageInfoDTO> result = mergeMainPageInfoResult(HeadLineResult, shopCategoryResult);
        return result;
    }

    private Result<MainPageInfoDTO> mergeMainPageInfoResult(Result<List<HeadLine>> headLineResult, Result<List<ShopCategory>> shopCategoryResult) {
        MainPageInfoDTO mainPageInfoDTO = new MainPageInfoDTO();
        mainPageInfoDTO.setHeadLineList(headLineResult.getData());
        mainPageInfoDTO.setShopCategoryList(shopCategoryResult.getData());

        Result<MainPageInfoDTO> result = new Result<>();
        result.setData(mainPageInfoDTO);
        result.setCode(200);
        result.setMsg("success");
        return result;
    }
}
