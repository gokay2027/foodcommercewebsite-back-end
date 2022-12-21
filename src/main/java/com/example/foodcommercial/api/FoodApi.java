package com.example.foodcommercial.api;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.example.foodcommercial.business.abstracts.IFoodService;
import com.example.foodcommercial.entities.Food;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequestMapping("/food")
public class FoodApi {

	private IFoodService foodService;

	@Autowired
	public FoodApi(IFoodService foodService) {
		this.foodService = foodService;
	}

	@GetMapping("/getall")
	public List<Food> getAllFood() {
		return this.foodService.getAllFood();
	}

	@PostMapping("/add")
	public void add(@Valid @RequestParam String name, @RequestParam int price,
					@RequestParam Long categoryId, @RequestParam Long restaurantId){
		this.foodService.add(name, price, categoryId, restaurantId);
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
