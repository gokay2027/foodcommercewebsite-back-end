package com.example.foodcommercial.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.foodcommercial.entities.Evaluation;
import com.example.foodcommercial.entities.Food;
import com.example.foodcommercial.entities.Restaurant;

public interface IRestaurantService {
	
	public List<Restaurant> getAllRestaurants();
	
	public Optional<Restaurant> getRestaurantById(Long id);
	
	public List<Food> getFoodListByRestaurantId(Long id);	
	
	public List<Evaluation> getEvaluationsByRestaurantId(Long id);

	public void add(String name, Long addressId);
	
}
