package com.example.foodcommercial.api;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.example.foodcommercial.business.abstracts.IRestaurantService;
import com.example.foodcommercial.entities.Evaluation;
import com.example.foodcommercial.entities.Food;
import com.example.foodcommercial.entities.Restaurant;

import javax.validation.Valid;
import javax.validation.ValidationException;

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

	@PostMapping("/add")
	public void add(@Valid @RequestParam String name, @RequestParam Long addressId){
		this.restaurantService.add(name,addressId);
	}

	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleValidationException(ValidationException exceptions) {

		return exceptions.getMessage();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Collection<String> handleValidationException(MethodArgumentNotValidException exceptions) {
		Map<String,String> validationErrors = new HashMap<String,String>();
		for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}

		return validationErrors.values();
	}

}
