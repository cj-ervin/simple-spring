package com.example.proj.service.solo.impl;

import com.example.proj.entity.bo.ShopCategory;
import com.example.proj.entity.dto.Result;
import com.example.proj.service.solo.ShopCategoryService;
import com.example.spring.core.annotation.Service;

import java.util.List;

/**
 * @Author ervin
 * @Date 2023/8/1
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Override
    public Result<Boolean> addShopCategory(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<Boolean> removeShopCategory(int shopCategoryId) {
        return null;
    }

    @Override
    public Result<Boolean> modifyShopCategory(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<ShopCategory> queryShopCategoryById(int shopCategoryId) {
        return null;
    }

    @Override
    public Result<List<ShopCategory>> queryShopCategory(ShopCategory shopCategoryCondition, int pageIndex, int pageSize) {
        return null;
    }
}
