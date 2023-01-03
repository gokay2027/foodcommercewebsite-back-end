package com.example.foodcommercial.api;

import com.example.foodcommercial.core.utilities.results.DataResult;
import com.example.foodcommercial.core.utilities.results.ErrorDataResult;
import com.example.foodcommercial.core.utilities.results.Result;
import com.example.foodcommercial.entities.FavoriteRestaurants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.example.foodcommercial.business.abstracts.IFavoriteRestaurantService;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/favoriterestaurants")
public class FavoriteRestaurantApi {

	private IFavoriteRestaurantService favoriteRestaurnatService;
	
	@Autowired
	public FavoriteRestaurantApi(IFavoriteRestaurantService favoriteRestaurnatService) {
		this.favoriteRestaurnatService=favoriteRestaurnatService;
	}
	
	
	@PostMapping("/addfavoriterestaurant")
	public Result addFavoriteRestaurant(@RequestParam Long userid, @RequestParam Long restaurantid) {
		return this.favoriteRestaurnatService.addFavoriteRestaurant(userid, restaurantid);
	}
	@GetMapping("/getAllFavoriteRestaurantsByUserId")
	public DataResult<List<FavoriteRestaurants>> getAllFavoriteRestaurantsByUserId(@RequestParam Long userId){
		return this.favoriteRestaurnatService.getAllFavoriteRestaurantsByUserId(userId);
	}
	@DeleteMapping("/delete")
	public Result delete(@RequestParam Long id){
		return this.favoriteRestaurnatService.delete(id);
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
