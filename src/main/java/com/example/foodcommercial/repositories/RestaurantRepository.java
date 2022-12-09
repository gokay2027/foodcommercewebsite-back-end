package com.example.foodcommercial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.foodcommercial.entities.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	
}
