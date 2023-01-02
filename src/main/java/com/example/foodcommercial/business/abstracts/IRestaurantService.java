package com.example.foodcommercial.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.foodcommercial.core.utilities.results.DataResult;
import com.example.foodcommercial.core.utilities.results.Result;
import com.example.foodcommercial.entities.*;

public interface IRestaurantService {
	
	public DataResult<List<Restaurant>> getAllRestaurants();
	
	public DataResult<Restaurant> getRestaurantById(Long id);
	
	public DataResult<List<Food>> getFoodListByRestaurantId(Long id);
	
	public DataResult<List<Evaluation>> getEvaluationsByRestaurantId(Long id);

	public DataResult<List<Restaurant>> getRestaurantsByNameContainsIgnoreCase(String name);

	public DataResult<List<Restaurant>> getRestaurantsByCategory(String categoryName);

	public Result addCategory(Long restaurantId, Long categoryId);
	public Result add(String name, String streetNo,
					  String hoodName, String buildingNumber, String district, String city);
	public Result addEvaluation(String content, int rateValue, Long restaurantId, Long userId);
	public DataResult<List<Category>> getAllCategories(Long restaurantId);
	public Result delete(Long id);
	
}
