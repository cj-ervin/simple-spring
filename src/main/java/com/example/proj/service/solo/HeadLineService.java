package com.example.proj.service.solo;

import com.example.proj.entity.bo.HeadLine;
import com.example.proj.entity.dto.Result;

import java.util.List;

/**
 * @Author ervin
 * @Date 2023/8/1
 */
public interface HeadLineService {

    Result<Boolean> addHeadLine(HeadLine headLine);

    Result<Boolean> removeHeadLine(int headLineId);

    Result<Boolean> modifyHeadLine(HeadLine headLine);

    Result<HeadLine> queryHeadLineById(int headLineId);

    Result<List<HeadLine>> queryHeadLine(HeadLine headLineCondition, int pageIndex, int pageSize);
}
