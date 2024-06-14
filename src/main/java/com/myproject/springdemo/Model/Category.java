package com.myproject.springdemo.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class Category extends BaseModel {
    private String name;
    private String desc;
    private List<Product> productList=new ArrayList<>();
}
