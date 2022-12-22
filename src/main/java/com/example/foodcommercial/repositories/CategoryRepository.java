package com.example.foodcommercial.repositories;

import com.example.foodcommercial.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category getCategoryByNameContainsIgnoreCase(String category);
    Category getCategoryById(Long id);
}
