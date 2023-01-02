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
	private FoodRepository foodRepo;
	private FavoriteRestaurantReposistory favoriteRestaurantRepo;

	@Autowired
	public RestaurantService(RestaurantRepository restaurantRepo,
							 AddressRepository addressRepository,
							 CategoryRepository categoryRepository,
							 EvaluationRepository evaluationRepository,
							 UserRepository userRepository,FoodRepository foodRepo,
							 FavoriteRestaurantReposistory favoriteRestaurantRepo) {
		this.restaurantRepo=restaurantRepo;
		this.addressRepository=addressRepository;
		this.categoryRepository=categoryRepository;
		this.evaluationRepository=evaluationRepository;
		this.userRepository=userRepository;
		this.foodRepo=foodRepo;
		this.favoriteRestaurantRepo=favoriteRestaurantRepo;
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

	@Override
	public DataResult<List<Restaurant>> getRestaurantsByCategory(String categoryName) {
		Category category = this.categoryRepository.getCategoryByNameContainsIgnoreCase(categoryName);
		if(category!=null) {
			List<Restaurant> restaurants = category.getRestaurants();
			return new SuccessDataResult<List<Restaurant>>(restaurants, "Restaurants by Category");
		}
		else return new ErrorDataResult<>("Error!");
	}

	@Override
	public Result add(String name, String streetNo,
					  String hoodName, String buildingNumber, String district, String city) {
		Address address = new Address(null,streetNo,hoodName,buildingNumber,district,city);
		Address address2 = this.addressRepository.save(address);
		Restaurant restaurant = new Restaurant(null, name, address2, null, null, null);
		this.restaurantRepo.save(restaurant);
		return new SuccessResult("Add Restaurant Successful!");

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
			return new SuccessResult("Save Successful!");
		}
		else return new ErrorResult("Error!");
	}

	@Override
	public DataResult<List<Category>> getAllCategories(Long restaurantId) {
		Restaurant restaurant = this.restaurantRepo.getRestaurantById(restaurantId);
		if(restaurant != null) {
			List<Category> categories = restaurant.getCategory();
			return new SuccessDataResult<>(categories, "Get All Categories Successful!");
		}
		else return new ErrorDataResult<>("Error!");
	}

	@Override
	public Result delete(Long id) {
		Restaurant restaurant = this.restaurantRepo.getRestaurantById(id);
		if(restaurant!=null){
			List<FavoriteRestaurants> favs = this.favoriteRestaurantRepo.findAll();
			for(FavoriteRestaurants favoriteRestaurant : favs){
				if(favoriteRestaurant.getRestaurant()==restaurant){
					this.favoriteRestaurantRepo.delete(favoriteRestaurant);
				}
			}
			this.restaurantRepo.delete(restaurant);
			return new SuccessResult("Deleted Successfully!");
		}
		else return new ErrorResult("Error!");
	}

}
