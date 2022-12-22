package com.example.foodcommercial.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.foodcommercial.core.utilities.results.DataResult;
import com.example.foodcommercial.core.utilities.results.Result;
import com.example.foodcommercial.entities.Evaluation;
import com.example.foodcommercial.entities.Food;
import com.example.foodcommercial.entities.Restaurant;

public interface IRestaurantService {
	
	public DataResult<List<Restaurant>> getAllRestaurants();
	
	public DataResult<Restaurant> getRestaurantById(Long id);
	
	public DataResult<List<Food>> getFoodListByRestaurantId(Long id);
	
	public DataResult<List<Evaluation>> getEvaluationsByRestaurantId(Long id);

	public DataResult<List<Restaurant>> getRestaurantsByNameContainsIgnoreCase(String name);

//	public DataResult<List<Restaurant>> getRestaurantsByCategoryContainsIgnoreCase(String category);

	public Result addCategory(Long restaurantId, Long categoryId);
	public Result add(String name, Long addressId);
	
}
