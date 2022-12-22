package com.example.foodcommercial.api;

import java.util.*;

import com.example.foodcommercial.core.utilities.results.DataResult;
import com.example.foodcommercial.core.utilities.results.Result;
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
	public DataResult<List<Restaurant>> getAllRestaurants() {
		return restaurantService.getAllRestaurants();
	}

	@GetMapping("/{id}")
	public DataResult<Restaurant> getRestaurantById(@PathVariable(name = "id") Long id) {
		return restaurantService.getRestaurantById(id);
	}

	@GetMapping("restaurantmenu/{id}")
	public DataResult<List<Food>> getRestaurantMenuById(@PathVariable(name = "id") Long id) {
		return restaurantService.getFoodListByRestaurantId(id);
	}
	
	@GetMapping("/restaurantevaluation/{id}")
	public DataResult<List<Evaluation>> getEvaluationByRestaurantId(@PathVariable(name = "id") Long id) {
		return this.restaurantService.getEvaluationsByRestaurantId(id);
	}

	@GetMapping("/restaurantbyname")
	public DataResult<List<Restaurant>> getRestaurantsByNameContainsIgnoreCase(String name){
		return this.restaurantService.getRestaurantsByNameContainsIgnoreCase(name);
	}

//	@GetMapping("/restaurantbycategory")
//	public DataResult<List<Restaurant>> getRestaurantsByCategoryContainsIgnoreCase(String category){
//		return this.restaurantService.getRestaurantsByCategoryContainsIgnoreCase(category);
//	}

	@PostMapping("/addCategory")
	public Result addCategory(Long restaurantId, Long categoryId){
		return this.restaurantService.addCategory(restaurantId, categoryId);
	}

	@PostMapping("/add")
	public Result add(@RequestParam String name, @RequestParam Long addressId){
		return this.restaurantService.add(name,addressId);
	}

	@PostMapping("/addEvaluation")
	public Result addEvaluation(@RequestParam String content, @RequestParam int rateValue,
								@RequestParam Long restaurantId,@RequestParam Long userId){
		return this.restaurantService.addEvaluation(content,rateValue,restaurantId,userId);
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
