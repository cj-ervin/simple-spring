package com.example.proj.service.solo;

import com.example.proj.entity.bo.ShopCategory;
import com.example.proj.entity.dto.Result;

import java.util.List;

/**
 * @Author ervin
 * @Date 2023/8/1
 */
public interface ShopCategoryService {


    Result<Boolean> addShopCategory(ShopCategory shopCategory);

    Result<Boolean> removeShopCategory(int shopCategoryId);

    Result<Boolean> modifyShopCategory(ShopCategory shopCategory);

    Result<ShopCategory> queryShopCategoryById(int shopCategoryId);

    Result<List<ShopCategory>> queryShopCategory(ShopCategory shopCategoryCondition, int pageIndex, int pageSize);
}
