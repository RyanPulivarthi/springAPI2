package com.myproject.springdemo.Services;

import com.myproject.springdemo.DTOs.FakeStoreDTO;
import com.myproject.springdemo.Model.Category;
import com.myproject.springdemo.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreApiService implements FakeStoreServiceInterface{
    @Autowired
    RestTemplateBuilder restTemplateBuilder;
    @Override
    public Product getProductById(Long productId){
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreDTO> responseEntity=restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreDTO.class,productId);

        if(responseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            return convertToProduct(responseEntity.getBody());
        }
       return null;
    }
    public List<Product> getAllProducts(){
        List<Product> productList=new ArrayList<>();
        RestTemplate restTemplate=restTemplateBuilder.build();
        FakeStoreDTO[] fakeStoreDTOS=restTemplate.getForEntity("https://fakestoreapi.com/products",FakeStoreDTO[].class).getBody();
        for(FakeStoreDTO fakeStoreDTO:fakeStoreDTOS){
            productList.add(convertToProduct(fakeStoreDTO));
        }

        return productList;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void createProduct(Product product) {
    }

    public Product convertToProduct(FakeStoreDTO fakeStoreDTO){
        Product product=new Product();
        product.setId(fakeStoreDTO.getId());
        product.setName(fakeStoreDTO.getTitle());
        product.setPrice(fakeStoreDTO.getPrice());
        product.setImgUrl(fakeStoreDTO.getImage());
        Category category=new Category();
        category.setName(fakeStoreDTO.getCategory());
        category.setDesc(fakeStoreDTO.getDescription());
        product.setCategory(category);
        product.setDesc(fakeStoreDTO.getDescription());
        return product;
    }
}
