package com.example.proj.entity.bo;

import lombok.Data;

import java.util.Date;

/**
 * 店铺类别
 * @author ervin
 */
@Data
public class ShopCategory {
    private Long shopCategoryId;
    private String shopCategoryName;
    private String shopCategoryDesc;
    private String shopCategoryImg;
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
    private ShopCategory parent;
}
