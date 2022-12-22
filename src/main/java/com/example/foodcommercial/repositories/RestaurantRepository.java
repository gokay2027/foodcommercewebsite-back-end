package com.example.foodcommercial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.foodcommercial.entities.Restaurant;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	Restaurant getRestaurantById(Long id);

	List<Restaurant> getRestaurantsByNameContainsIgnoreCase(String name);

//	@Query("SELECT r FROM Restaurant r WHERE  r.category =: category")
//	List<Restaurant> getRestaurantsByCategoryContainsIgnoreCase(String category);
}
