package com.myproject.springdemo.DTOs;

import com.myproject.springdemo.Model.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO extends BaseModel {
    private String name;
    private String description;
}
