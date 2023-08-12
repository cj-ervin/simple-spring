package org.springframework.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @Author ervin
 * @Date 2023/8/5
 */
@Data
@Component
public class Book {

    private int price;

    private LocalDate produceDate;

    @Value("${name}")
    private String name;

    private String tag;
}
