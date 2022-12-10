package com.example.foodcommercial.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodcommercial.business.abstracts.IFavoriteRestaurantService;

@RestController
@RequestMapping("/favoriterestaurants")
public class FavoriteRestaurantApi {

	private IFavoriteRestaurantService favoriteRestaurnatService;
	
	@Autowired
	public FavoriteRestaurantApi(IFavoriteRestaurantService favoriteRestaurnatService) {
		this.favoriteRestaurnatService=favoriteRestaurnatService;
	}
	
	
	
	@PostMapping("/addfavoriterestaurant/{userid}/{restaurantid}")
	public void addFavoriteRestaurant(@PathVariable(value="userid") Long userid,@PathVariable(value="restaurantid") Long restaurantid) {
		this.favoriteRestaurnatService.addFavoriteRestaurant(userid, restaurantid);
	}
	
}
