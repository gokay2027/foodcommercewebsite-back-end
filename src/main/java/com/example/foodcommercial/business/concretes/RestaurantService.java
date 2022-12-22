package com.example.foodcommercial.business.concretes;

import java.util.List;
import java.util.Optional;

import com.example.foodcommercial.core.utilities.results.*;
import com.example.foodcommercial.entities.*;
import com.example.foodcommercial.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foodcommercial.business.abstracts.IRestaurantService;

@Service
public class RestaurantService implements IRestaurantService {
	
	private RestaurantRepository restaurantRepo;
	private AddressRepository addressRepository;
	private CategoryRepository categoryRepository;
	private EvaluationRepository evaluationRepository;
	private UserRepository userRepository;
	
	@Autowired
	public RestaurantService(RestaurantRepository restaurantRepo,
							 AddressRepository addressRepository,
							 CategoryRepository categoryRepository,
							 EvaluationRepository evaluationRepository,
							 UserRepository userRepository) {
		this.restaurantRepo=restaurantRepo;
		this.addressRepository=addressRepository;
		this.categoryRepository=categoryRepository;
		this.evaluationRepository=evaluationRepository;
		this.userRepository=userRepository;
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
			restaurant.getCategory().add(category);
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

	@Override
	public Result addEvaluation(String content, int rateValue, Long restaurantId, Long userId) {
		User user = this.userRepository.getUserById(userId);
		Restaurant restaurant = this.restaurantRepo.getRestaurantById(restaurantId);
		Evaluation evaluation = new Evaluation(null, content ,rateValue, user, restaurant);
		if(user != null && restaurant != null) {
			user.getEvaluations().add(evaluation);
			this.userRepository.save(user);
			restaurant.getEvaluations().add(evaluation);
			this.restaurantRepo.save(restaurant);
			this.evaluationRepository.save(evaluation);
			return new SuccessResult("Save Successful!");
		}
		else return new ErrorResult("Error!");
	}
}
