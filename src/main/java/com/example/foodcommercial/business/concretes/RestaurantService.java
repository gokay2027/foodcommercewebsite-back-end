package com.example.foodcommercial.business.concretes;

import java.util.List;
import java.util.Optional;

import com.example.foodcommercial.core.utilities.results.*;
import com.example.foodcommercial.entities.*;
import com.example.foodcommercial.repositories.AddressRepository;
import com.example.foodcommercial.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foodcommercial.business.abstracts.IRestaurantService;
import com.example.foodcommercial.repositories.RestaurantRepository;

@Service
public class RestaurantService implements IRestaurantService {
	
	RestaurantRepository restaurantRepo;
	AddressRepository addressRepository;
	CategoryRepository categoryRepository;
	
	@Autowired
	public RestaurantService(RestaurantRepository restaurantRepo,
							 AddressRepository addressRepository,
							 CategoryRepository categoryRepository) {
		this.restaurantRepo=restaurantRepo;
		this.addressRepository=addressRepository;
		this.categoryRepository=categoryRepository;
	}

	@Override
	public DataResult<List<Restaurant>> getAllRestaurants() {
		return new SuccessDataResult<>(restaurantRepo.findAll());
	}

	@Override
	public DataResult<Restaurant> getRestaurantById(Long id) {
		Restaurant restaurant = restaurantRepo.getRestaurantById(id);
		if(restaurant != null)
			return new SuccessDataResult<Restaurant>(restaurant, "Successful Search!");
		else return new ErrorDataResult<>("Restaurant Id is invalid!");
	}

	@Override
	public DataResult<List<Food>> getFoodListByRestaurantId(Long id) {
		return new SuccessDataResult<>(restaurantRepo.getRestaurantById(id).getFoods());
	}

	@Override
	public DataResult<List<Evaluation>> getEvaluationsByRestaurantId(Long id) {
		return new SuccessDataResult<>(this.restaurantRepo.getRestaurantById(id).getEvaluations());
	}

	@Override
	public DataResult<List<Restaurant>> getRestaurantsByNameContainsIgnoreCase(String name) {
		List<Restaurant> restaurants =
				restaurantRepo.getRestaurantsByNameContainsIgnoreCase(name);
		if(restaurants != null)
			return new SuccessDataResult<>(restaurants, "Search Successful!");
		else return new ErrorDataResult<>("Search Not Found!");
	}

	@Override
	public Result addCategory(Long restaurantId, Long categoryId) {
		Category category = categoryRepository.getCategoryById(categoryId);
		Restaurant restaurant = restaurantRepo.getRestaurantById(restaurantId);
		if(category != null && restaurant != null){
			restaurant.setCategory(category);
			restaurantRepo.save(restaurant);
			return new SuccessResult("Add Category to Restaurant Successful!");
		}
		else return new ErrorDataResult<>("Invalid input!");
	}

//	@Override
//	public DataResult<List<Restaurant>> getRestaurantsByCategoryContainsIgnoreCase(String category) {
//		List<Restaurant> restaurants =
//				restaurantRepo.getRestaurantsByCategoryContainsIgnoreCase(category);
//		if(restaurants != null)
//			return new SuccessDataResult<>(restaurants, "Search Successful!");
//		else return new ErrorDataResult<>("Search Not Found!");	}

	@Override
	public Result add(String name, Long addressId) {
		Address address = addressRepository.getAddressById(addressId);
		if(address != null) {
			Restaurant restaurant = new Restaurant(null, name, address, null, null, null);
			this.restaurantRepo.save(restaurant);
			return new SuccessResult("Add Restaurant Successful!");
		}
		else return new ErrorDataResult<>("Address Id is invalid!");
	}
}
