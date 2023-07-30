package com.example.proj.entity.dto;


import com.example.proj.entity.bo.HeadLine;
import com.example.proj.entity.bo.ShopCategory;
import lombok.Data;

import java.util.List;

/**
 *
 * @author ervin
 */
@Data
public class MainPageInfoDTO {
    private List<HeadLine> headLineList;
    private List<ShopCategory> shopCategoryList;
}
