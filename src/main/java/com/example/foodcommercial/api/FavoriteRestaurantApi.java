package com.example.foodcommercial.api;

import com.example.foodcommercial.core.utilities.results.ErrorDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.example.foodcommercial.business.abstracts.IFavoriteRestaurantService;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;

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
