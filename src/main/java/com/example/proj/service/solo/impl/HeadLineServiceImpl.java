package com.example.proj.service.solo.impl;

import com.example.proj.entity.bo.HeadLine;
import com.example.proj.entity.dto.Result;
import com.example.proj.service.solo.HeadLineService;
import com.example.spring.core.annotation.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ervin
 * @Date 2023/8/1
 */
@Slf4j
@Service
public class HeadLineServiceImpl implements HeadLineService {
    @Override
    public Result<Boolean> addHeadLine(HeadLine headLine) {
        log.info("addHeadLine被执行啦, lineName[{}],lineLink[{}],lineImg[{}], priority[{}]",
                headLine.getLineName(), headLine.getLineLink(), headLine.getLineImg(), headLine.getPriority());
        Result<Boolean> result = new Result<Boolean>();
        result.setCode(200);
        result.setMsg("请求成功啦");
        result.setData(true);
        return result;
    }

    @Override
    public Result<Boolean> removeHeadLine(int headLineId) {
        return null;
    }

    @Override
    public Result<Boolean> modifyHeadLine(HeadLine headLine) {
        return null;
    }

    @Override
    public Result<HeadLine> queryHeadLineById(int headLineId) {
        return null;
    }

    @Override
    public Result<List<HeadLine>> queryHeadLine(HeadLine headLineCondition, int pageIndex, int pageSize) {
        List<HeadLine> headLineList = new ArrayList<>();
        HeadLine headLine1 = new HeadLine();
        headLine1.setLineId(1L);
        headLine1.setLineName("头条1");
        headLine1.setLineLink("www.baidu.com");
        headLine1.setLineImg("头条图片1地址");
        headLineList.add(headLine1);
        HeadLine headLine2 = new HeadLine();
        headLine2.setLineId(2L);
        headLine2.setLineName("头条2");
        headLine2.setLineLink("www.google.com");
        headLine2.setLineImg("头条图片2地址");
        headLineList.add(headLine2);
        Result<List<HeadLine>> result = new Result<>();
        result.setData(headLineList);
        result.setCode(200);
        return result;
    }
}
