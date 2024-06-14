package com.myproject.springdemo.Model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{
    private String name;
    private String desc;
    private String imgUrl;
    private Double price;
    private Category category;
}
