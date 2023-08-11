package org.springframework.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author ervin
 * @Date 2023/8/5
 */
@Data
@Component
public class Book {

    @Value("${name}")
    private String name;

    private String tag;
}
