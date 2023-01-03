package com.example.foodcommercial.api;

import java.util.*;

import com.example.foodcommercial.core.utilities.results.DataResult;
import com.example.foodcommercial.core.utilities.results.ErrorDataResult;
import com.example.foodcommercial.core.utilities.results.Result;
import com.example.foodcommercial.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.example.foodcommercial.business.abstracts.IRestaurantService;

import javax.validation.Valid;
import javax.validation.ValidationException;


@CrossOrigin
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

	@GetMapping("/restaurantById")
	public DataResult<Restaurant> getRestaurantById(@RequestParam Long id) {
		return restaurantService.getRestaurantById(id);
	}

	@GetMapping("/restaurantmenu")
	public DataResult<List<Food>> getRestaurantMenuById(@RequestParam Long id) {
		return restaurantService.getFoodListByRestaurantId(id);
	}
	
	@GetMapping("/restaurantevaluation")
	public DataResult<List<Evaluation>> getEvaluationByRestaurantId(@RequestParam Long id) {
		return this.restaurantService.getEvaluationsByRestaurantId(id);
	}

	@GetMapping("/restaurantbyname")
	public DataResult<List<Restaurant>> getRestaurantsByNameContainsIgnoreCase(@RequestParam String name){
		return this.restaurantService.getRestaurantsByNameContainsIgnoreCase(name);
	}

	@GetMapping("/getRestaurantsByCategory")
	public DataResult<List<Restaurant>> getRestaurantsByCategory(@RequestParam String categoryName){
		return this.restaurantService.getRestaurantsByCategory(categoryName);
	}

	@PutMapping("/addCategory")
	public Result addCategory(@RequestParam Long restaurantId,@RequestParam Long categoryId){
		return this.restaurantService.addCategory(restaurantId, categoryId);
	}

	@PostMapping("/add")
	public Result add(@RequestParam String name,@RequestParam String streetNo,
					  @RequestParam String hoodName,@RequestParam String buildingNumber,
					  @RequestParam String district,@RequestParam String city){
		return this.restaurantService.add(name,streetNo,
				hoodName,buildingNumber,district,city);
	}

	@PostMapping("/addEvaluation")
	public Result addEvaluation(@RequestParam String content, @RequestParam int rateValue,
								@RequestParam Long restaurantId,@RequestParam Long userId){
		return this.restaurantService.addEvaluation(content,rateValue,restaurantId,userId);
	}
	@GetMapping("/getAllCategories")
	public DataResult<List<Category>> getAllCategories(@RequestParam Long restaurantId){
		return this.restaurantService.getAllCategories(restaurantId);
	}
	@DeleteMapping("/delete")
	public Result delete(@RequestParam Long id){
		return this.restaurantService.delete(id);
	}

	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(ValidationException exceptions) {

		ErrorDataResult<Object> errors =
				new ErrorDataResult<Object>(exceptions.getMessage(), "Validation Errors!");
		return errors;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
		Map<String,String> validationErrors = new HashMap<String,String>();
		for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		ErrorDataResult<Object> errors =
				new ErrorDataResult<Object>(validationErrors, "Validation Errors!");
		return errors;
	}

}
