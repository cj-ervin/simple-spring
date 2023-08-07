package com.example.spring.aop.aspect;

import com.example.spring.aop.PointcutLocator;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author ervin
 * @Date 2023/8/7
 */
@AllArgsConstructor
@Data
public class AspectInfo {

    private int orderIndex;

    private DefaultAspect defaultAspect;

    private PointcutLocator pointcutLocator;
}
