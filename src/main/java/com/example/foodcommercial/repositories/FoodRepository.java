package com.example.foodcommercial.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.foodcommercial.entities.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {

    Food getFoodById(Long id);

	
}
