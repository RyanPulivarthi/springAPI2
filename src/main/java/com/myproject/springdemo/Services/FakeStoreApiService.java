package com.myproject.springdemo.Services;

import com.myproject.springdemo.DTOs.CategoryDTO;
import com.myproject.springdemo.DTOs.FakeStoreDTO;
import com.myproject.springdemo.Model.Category;
import com.myproject.springdemo.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
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
        FakeStoreDTO fakeStoreDTO=getFakeStoreDTO(product);
        FakeStoreDTO fakeStoreDTOresponse=requestForEntity("https://fakestoreapi.com/products/{id}",HttpMethod.PUT,fakeStoreDTO,FakeStoreDTO.class,id).getBody();
        return convertToProduct(fakeStoreDTOresponse);
    }


    @Override
    public Product createProduct(Product product) {
        return null;
    }
    public <T> ResponseEntity<T> requestForEntity(String url,HttpMethod httpMethod,Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate=restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    public Product convertToProduct(FakeStoreDTO fakeStoreDTO){
        Product product=new Product();
        product.setId(fakeStoreDTO.getId());
        product.setName(fakeStoreDTO.getTitle());
        product.setPrice(fakeStoreDTO.getPrice());
        product.setImgUrl(fakeStoreDTO.getImage());
        Category category=new Category();
        category.setName(fakeStoreDTO.getCategory());
        category.setDescription(fakeStoreDTO.getDescription());
        product.setCategory(category);
        product.setDescription(fakeStoreDTO.getDescription());
        return product;
    }
    public FakeStoreDTO getFakeStoreDTO(Product product){
        FakeStoreDTO fakeStoreDTO=new FakeStoreDTO();
        fakeStoreDTO.setId(product.getId());
        fakeStoreDTO.setDescription(product.getDescription());
        fakeStoreDTO.setTitle(product.getName());
        fakeStoreDTO.setPrice(product.getPrice());
        fakeStoreDTO.setImage(product.getImgUrl());
        CategoryDTO categoryDTO=new CategoryDTO();
        categoryDTO.setName(product.getCategory().getName());
        categoryDTO.setDesc(product.getCategory().getDescription());
        fakeStoreDTO.setCategory(categoryDTO.getName());
        return fakeStoreDTO;
    }
}