package com.myproject.springdemo.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.PrivateKey;
@Getter
@Setter
public class FakeStoreDTO {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
    private FakeStoreRatingDTO rating;
}
