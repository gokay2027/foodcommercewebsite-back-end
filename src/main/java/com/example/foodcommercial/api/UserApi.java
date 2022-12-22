package com.example.foodcommercial.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.ValidationException;

import com.example.foodcommercial.core.utilities.results.DataResult;
import com.example.foodcommercial.core.utilities.results.ErrorDataResult;
import com.example.foodcommercial.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodcommercial.business.concretes.UserService;
import com.example.foodcommercial.entities.Address;
import com.example.foodcommercial.entities.CardInformation;
import com.example.foodcommercial.entities.FavoriteRestaurants;
import com.example.foodcommercial.entities.User;

@RestController
@RequestMapping("/user")
public class UserApi {

	private UserService userService;

	@Autowired
	public UserApi(UserService userService) {
		super();
		this.userService = userService;
	}

	// User login
	@GetMapping("/{email}/{password}")
	public DataResult<User> logginUser(@PathVariable(value = "email") String email,
									   @PathVariable(value = "password") String password) {
		return this.userService.loginUser(email, password);
	}

	// User register
	@PostMapping(value = "/register")
	public Result registerUser(@RequestParam String name, @RequestParam String surname, @RequestParam String email,
							   @RequestParam String password, @RequestParam String phoneNumber, @RequestParam String birthDate) {
		return this.userService.registerUser(name, surname, email, password, phoneNumber, birthDate);
	}

	// User adds adress
	@PostMapping(value = "/addadress/{id}")
	public Result addUserAdress(@PathVariable(value = "id") Long id, @Valid @RequestBody Address address) {
		return this.userService.addUserAdress(id, address);
	}

	@GetMapping("/getcards/{id}")
	public DataResult<List<CardInformation>> getCardsOfUser(@PathVariable(value = "id") Long id) {
		return this.userService.getCardsOfUser(id);
	}

	@GetMapping("/getfavoriterestaurants/{id}")
	public DataResult<List<FavoriteRestaurants>> getFavoriteRestaurantsByUserId(@PathVariable(value = "id") Long id) {
		return this.userService.getFavoriteRestaurants(id);
	}

	@GetMapping("/getuseradresses")
	public DataResult<List<Address>> getUserAdresses(@RequestParam Long id) {
		return this.userService.getUserAdresses(id);
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

	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)	
	public ErrorDataResult<Object> handleValidationException(ValidationException exceptions) {

		ErrorDataResult<Object> errors =
				new ErrorDataResult<Object>(exceptions.getMessage(), "Validation Errors!");
		return errors;
	}
	



}