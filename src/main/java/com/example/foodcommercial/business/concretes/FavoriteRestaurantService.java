package com.example.foodcommercial.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foodcommercial.business.abstracts.IFavoriteRestaurantService;
import com.example.foodcommercial.entities.FavoriteRestaurants;
import com.example.foodcommercial.entities.Restaurant;
import com.example.foodcommercial.entities.User;
import com.example.foodcommercial.repositories.FavoriteRestaurantReposistory;
import com.example.foodcommercial.repositories.RestaurantRepository;
import com.example.foodcommercial.repositories.UserRepository;

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
	public void addFavoriteRestaurant(Long userid, Long favoriteRestaurantId) {
		// TODO Auto-generated method stub

		Restaurant restaurant = this.restaurantRepo.findById(favoriteRestaurantId).get();

		User user = this.userRepo.findById(userid).get();

		FavoriteRestaurants favoriteRestaurant = new FavoriteRestaurants(null, restaurant, user);

		this.favoriteRestaurantRepo.save(favoriteRestaurant);

	}
}
