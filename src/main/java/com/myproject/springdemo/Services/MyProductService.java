package com.myproject.springdemo.Services;

import com.myproject.springdemo.DAOs.CategoryRepo;
import com.myproject.springdemo.DAOs.ProductRepo;
import com.myproject.springdemo.Model.Category;
import com.myproject.springdemo.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class MyProductService implements FakeStoreServiceInterface{
    @Autowired
    ProductRepo productRepo;
    @Autowired
    CategoryRepo caRepo;
    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct=productRepo.findById(id);
        if(optionalProduct.isEmpty())
            return null;
        return optionalProduct.get();
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Category> optionalCategory=caRepo.findById(product.getCategory().getId());
        if(optionalCategory.isPresent())

            return productRepo.save(product);
        else{
            caRepo.save(product.getCategory());
        }
       return productRepo.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products=productRepo.findAll();
        return products;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }
}
