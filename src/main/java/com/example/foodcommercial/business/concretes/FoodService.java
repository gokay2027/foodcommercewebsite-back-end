package com.example.foodcommercial.business.concretes;

import java.util.List;
import java.util.Optional;

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
	
	@Autowired
	public FoodService(FoodRepository foodRepo, CategoryRepository categoryRepo,
					   RestaurantRepository restaurantRepo)
	{
		this.foodRepo=foodRepo;
		this.categoryRepo=categoryRepo;
		this.restaurantRepo=restaurantRepo;
	}
	
	
	@Override
	public List<Food> getAllFood() {
		// TODO Auto-generated method stub
		return this.foodRepo.findAll();
	}

	@Override
	public void add(String name, int price, Long categoryId, Long restaurantId) {
		Category category = this.categoryRepo.getReferenceById(categoryId);
		Restaurant restaurant = this.restaurantRepo.getReferenceById(restaurantId);

		Food food = new Food(null, name, price, category, null, restaurant);
		this.foodRepo.save(food);
	}


}
