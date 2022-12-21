package com.example.foodcommercial.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.foodcommercial.entities.Food;

public interface IFoodService {
	
	List<Food> getAllFood();

	void add(String name, int price, Long categoryId, Long restaurantId);
	
}
