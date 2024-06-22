package com.myproject.springdemo.DAOs;

import com.myproject.springdemo.Model.Category;
import com.myproject.springdemo.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
     Optional<Category> findById(Long id);

    Category save(Category category);

}
