package com.myproject.springdemo.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private String imgUrl;
    private Double price;
    private CategoryDTO categoryDTO;
}
