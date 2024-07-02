package com.myproject.springdemo.Controller;

import com.myproject.springdemo.DTOs.ProductDTO;
import com.myproject.springdemo.Model.Category;
import com.myproject.springdemo.Model.Product;
import com.myproject.springdemo.Services.FakeStoreServiceInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;
    @MockBean
    private FakeStoreServiceInterface fakeStoreServiceInterface;
@DisplayName("Returnig products for given id")
    @Test
    void Test_GetProductById_Returns_Product_Successfully() {
        //Assign
        Product product=new Product();
        product.setId(2L);
        product.setName("test product");
        Category category=new Category();
        category.setId(2L);
        category.setName("just category");
        product.setCategory(category);
        when(fakeStoreServiceInterface.getProductById(any(Long.class))).thenReturn(product);


        //Act
        ResponseEntity<ProductDTO> productDTOResponseEntity= productController.getProduct(16L);

        //Assert
        assertNotNull(productDTOResponseEntity);
        System.out.println(productDTOResponseEntity.getBody());
        assertEquals("test product",productDTOResponseEntity.getBody().getName());

    }
    @DisplayName("Throwing IlleagalArgument exception for id 0")
    @Test
    void Test_Exception_Scenario_for_Get_Product_By_Id(){
        //when(fakeStoreServiceInterface.getProductById(0L)).thenThrow(new IllegalArgumentException("invalid id"));
        Exception e=assertThrows(IllegalArgumentException.class,()->productController.getProduct(0L));
        assertEquals("invalid id",e.getMessage());

    }
}