package com.example.foodcommercial.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.foodcommercial.core.utilities.results.DataResult;
import com.example.foodcommercial.core.utilities.results.Result;
import com.example.foodcommercial.entities.Food;

public interface IFoodService {
	DataResult<List<Food>> getAllFood();
	Result add(String name, int price, Long categoryId, Long restaurantId);
	Result addPortion(Long foodId, Long portionId);
	Result delete(Long id);
	
}
