package com.example.foodcommercial.business.concretes;

import java.util.List;
import java.util.Optional;

import com.example.foodcommercial.core.utilities.results.*;
import com.example.foodcommercial.entities.Category;
import com.example.foodcommercial.entities.Portion;
import com.example.foodcommercial.entities.Restaurant;
import com.example.foodcommercial.repositories.CategoryRepository;
import com.example.foodcommercial.repositories.PortionRepository;
import com.example.foodcommercial.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foodcommercial.business.abstracts.IFoodService;
import com.example.foodcommercial.entities.Food;
import com.example.foodcommercial.repositories.FoodRepository;

@Service
public class FoodService implements IFoodService {

	private FoodRepository foodRepo;
	private CategoryRepository categoryRepo;
	private RestaurantRepository restaurantRepo;
	private PortionRepository portionRepo;
	
	@Autowired
	public FoodService(FoodRepository foodRepo, CategoryRepository categoryRepo,
					   RestaurantRepository restaurantRepo, PortionRepository portionRepo)
	{
		this.foodRepo=foodRepo;
		this.categoryRepo=categoryRepo;
		this.restaurantRepo=restaurantRepo;
		this.portionRepo=portionRepo;
	}
	
	
	@Override
	public DataResult<List<Food>> getAllFood() {
		return new SuccessDataResult<>(this.foodRepo.findAll());
	}

	@Override
	public Result add(String name, int price, Long categoryId, Long restaurantId) {
		Category category = this.categoryRepo.getCategoryById(categoryId);
		Restaurant restaurant = this.restaurantRepo.getRestaurantById(restaurantId);

		if(category != null && restaurant != null) {
			Food food = new Food(null, name, price, category, null, restaurant);
			restaurant.getFoods().add(food);
			this.foodRepo.save(food);
			return new SuccessResult("Add Food Successful!");
		}
		else return new ErrorResult("Error!");
	}

	@Override
	public Result addPortion(Long foodId, Long portionId) {
		Portion portion = this.portionRepo.getPortionById(portionId);
		Food food = this.foodRepo.getFoodById(foodId);
		if(portion != null && food != null){
			food.getPortion().add(portion);
			this.foodRepo.save(food);
			return new SuccessResult("Add Portion to Food Successful!");
		}
		else return new ErrorResult("Error!");
	}


}
