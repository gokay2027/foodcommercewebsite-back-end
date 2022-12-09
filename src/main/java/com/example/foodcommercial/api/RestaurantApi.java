package com.example.foodcommercial.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodcommercial.business.abstracts.IRestaurantService;
import com.example.foodcommercial.entities.Evaluation;
import com.example.foodcommercial.entities.Food;
import com.example.foodcommercial.entities.Restaurant;

@RestController
@RequestMapping("/restaurant")
public class RestaurantApi {

	IRestaurantService restaurantService;

	@Autowired
	public RestaurantApi(IRestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	@GetMapping("/getall")
	public List<Restaurant> getAllRestaurants() {
		return restaurantService.getAllRestaurants();
	}

	@GetMapping("/{id}")
	public Optional<Restaurant> getRestaurantById(@PathVariable(name = "id") Long id) {
		return restaurantService.getRestaurantById(id);
	}

	@GetMapping("restaurantmenu/{id}")
	public List<Food> getRestaurantMenuById(@PathVariable(name = "id") Long id) {
		return restaurantService.getFoodListByRestaurantId(id);
	}
	
	@GetMapping("/restaurantevaluation/{id}")
	public List<Evaluation> getEvaluationByRestaurantId(@PathVariable(name = "id") Long id) {
		return this.restaurantService.getEvaluationsByRestaurantId(id);
	}

}
