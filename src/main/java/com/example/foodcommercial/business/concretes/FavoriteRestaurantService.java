package com.example.foodcommercial.business.concretes;

import com.example.foodcommercial.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foodcommercial.business.abstracts.IFavoriteRestaurantService;
import com.example.foodcommercial.entities.FavoriteRestaurants;
import com.example.foodcommercial.entities.Restaurant;
import com.example.foodcommercial.entities.User;
import com.example.foodcommercial.repositories.FavoriteRestaurantReposistory;
import com.example.foodcommercial.repositories.RestaurantRepository;
import com.example.foodcommercial.repositories.UserRepository;

import java.util.List;

@Service
public class FavoriteRestaurantService implements IFavoriteRestaurantService {

	private FavoriteRestaurantReposistory favoriteRestaurantRepo;
	private UserRepository userRepo;
	private RestaurantRepository restaurantRepo;

	@Autowired
	public FavoriteRestaurantService(FavoriteRestaurantReposistory favoriteRestaurantRepo,
			UserRepository userRepo,
			RestaurantRepository restaurantRepo) {
		this.favoriteRestaurantRepo = favoriteRestaurantRepo;
		this.userRepo = userRepo;
		this.restaurantRepo = restaurantRepo;
	}

	@Override
	public Result addFavoriteRestaurant(Long userid, Long favoriteRestaurantId) {

		Restaurant restaurant = this.restaurantRepo.getRestaurantById(favoriteRestaurantId);
		User user = this.userRepo.getUserById(userid);
		if(restaurant != null && user != null) {
			FavoriteRestaurants favoriteRestaurant = new FavoriteRestaurants(null, restaurant, user);

			this.favoriteRestaurantRepo.save(favoriteRestaurant);
			return new SuccessResult("Add Restaurant to Favorite Successful!");
		}
		else return new ErrorResult("Error!");
	}

	@Override
	public DataResult<List<FavoriteRestaurants>> getAllFavoriteRestaurantsByUserId(Long id) {
		User user = this.userRepo.getUserById(id);
		if(user != null){
			return new SuccessDataResult<List<FavoriteRestaurants>>
					(this.favoriteRestaurantRepo.getFavoriteRestaurantsByUser(user), "Favorite Restaurants");
		}
		else return new ErrorDataResult<>("Error!");
	}

	@Override
	public Result delete(Long id) {
		FavoriteRestaurants restaurant = this.favoriteRestaurantRepo.getFavoriteRestaurantsById(id);
		if(restaurant!=null){
			this.favoriteRestaurantRepo.delete(restaurant);
			return new SuccessResult("Deleted Successfully!");
		}
		else return new ErrorResult("Error!");
	}
}
