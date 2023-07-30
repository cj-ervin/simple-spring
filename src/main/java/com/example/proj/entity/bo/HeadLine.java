package com.example.proj.entity.bo;

import lombok.Data;

import java.util.Date;

/**
 * 头条
 * @author ervin
 */
@Data
public class HeadLine {
    private Long lineId;
    private String lineName;
    private String lineLink;
    private String lineImg;
    private Integer priority;
    private Integer enableStatus;
    private Date createTime;
    private Date lastEditTime;
}
