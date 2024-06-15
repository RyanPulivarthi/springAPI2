package com.myproject.springdemo.Controller;

import com.myproject.springdemo.DTOs.CategoryDTO;
import com.myproject.springdemo.DTOs.FakeStoreDTO;
import com.myproject.springdemo.DTOs.ProductDTO;
import com.myproject.springdemo.Model.Category;
import com.myproject.springdemo.Model.Product;
import com.myproject.springdemo.Services.FakeStoreApiService;
import com.myproject.springdemo.Services.FakeStoreServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    FakeStoreServiceInterface fakeStoreServiceInterface;
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id){
        try {
            if (id <= 0)
                throw new IllegalArgumentException("Invalid id");
            Product product = fakeStoreServiceInterface.getProductById(id);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            headers.add("Created By", "Ryan");
            return new ResponseEntity<>(getProductDTO(product), headers, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("This is in catch block");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/products")
    public List<ProductDTO> getProducts(){
        List<Product> products=fakeStoreServiceInterface.getAllProducts();
        List<ProductDTO> productDTOList=new ArrayList<>();
        for(Product product:products) {
            productDTOList.add(getProductDTO(product));
        }
        return productDTOList;
    }
    @PutMapping("/products/{id}")
    public ProductDTO replaceProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        Product product=convertToProduct(productDTO);
        Product newProduct=fakeStoreServiceInterface.replaceProduct(id,product);
        return getProductDTO(newProduct);

    }

    public ProductDTO getProductDTO(Product product){
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setDesc(product.getDesc());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setImgUrl(product.getImgUrl());
        CategoryDTO categoryDTO=new CategoryDTO();
        categoryDTO.setName(product.getCategory().getName());
        categoryDTO.setDesc(product.getCategory().getDesc());
        productDTO.setCategoryDTO(categoryDTO);
        return productDTO;
    }
    public Product convertToProduct(ProductDTO productDTO){
        Product product=new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());
        Category category=new Category();
        category.setName(productDTO.getCategoryDTO().getName());
        category.setDesc(productDTO.getDesc());
        product.setCategory(category);
        product.setDesc(productDTO.getDesc());
        return product;
    }
}
