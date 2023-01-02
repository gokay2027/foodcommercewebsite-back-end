package com.example.foodcommercial.business.abstracts;

import com.example.foodcommercial.core.utilities.results.DataResult;
import com.example.foodcommercial.core.utilities.results.Result;
import com.example.foodcommercial.entities.FavoriteRestaurants;
import com.example.foodcommercial.entities.Food;

import java.util.List;

public interface IFavoriteRestaurantService {
	
	Result addFavoriteRestaurant(Long userid, Long favoriteRestaurantId);
	DataResult<List<FavoriteRestaurants>> getAllFavoriteRestaurantsByUserId(Long id);
	Result delete(Long id);

}
