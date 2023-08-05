package org.springframework.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author ervin
 * @Date 2023/8/5
 */
@Data
public class Student {

    private String name;

    private int age;

    private Book book;
}
