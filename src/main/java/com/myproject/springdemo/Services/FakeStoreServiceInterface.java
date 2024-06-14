package com.myproject.springdemo.Services;

import com.myproject.springdemo.Model.Product;

import java.util.List;

public interface FakeStoreServiceInterface {
    Product getProductById(Long id);
    void createProduct(Product product);
    List<Product> getAllProducts();
}
