package com.myproject.springdemo.DAOs;

import com.myproject.springdemo.Model.Product;
import org.hibernate.Remove;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
     Optional<Product> findById(Long id);

    Product save(Product product);

}
