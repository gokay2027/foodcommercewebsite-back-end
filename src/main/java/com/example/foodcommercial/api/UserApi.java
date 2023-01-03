package com.example.foodcommercial.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.ValidationException;

import com.example.foodcommercial.business.abstracts.IUserService;
import com.example.foodcommercial.core.utilities.results.DataResult;
import com.example.foodcommercial.core.utilities.results.ErrorDataResult;
import com.example.foodcommercial.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.example.foodcommercial.entities.Address;
import com.example.foodcommercial.entities.CardInformation;
import com.example.foodcommercial.entities.FavoriteRestaurants;
import com.example.foodcommercial.entities.User;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserApi {

	private IUserService userService;

	@Autowired
	public UserApi(IUserService userService) {
		super();
		this.userService = userService;
	}

	// User login
	@GetMapping("/login")
	public DataResult<User> logginUser(@RequestParam(value = "email") String email,
									   @RequestParam(value = "password") String password) {
		return this.userService.loginUser(email, password);
	}

	// User register
	@PostMapping(value = "/register")
	public Result registerUser(@RequestParam String name, @RequestParam String surname, @RequestParam String email,
							   @RequestParam String password, @RequestParam String phoneNumber, @RequestParam String birthDate) {
		return this.userService.registerUser(name, surname, email, password, phoneNumber, birthDate);
	}

	// User adds adress
	@PutMapping(value = "/addadress")
	public Result addUserAdress(@RequestParam(value = "id") Long id, @RequestParam String streetNo,
								@RequestParam String hoodName,@RequestParam String buildingNumber,
								@RequestParam String district,@RequestParam String city) {
		return this.userService.addUserAdress(id,streetNo,
				hoodName,buildingNumber,district,city);
	}

	@GetMapping("/getcards")
	public DataResult<List<CardInformation>> getCardsOfUser(@RequestParam Long id) {
		return this.userService.getCardsOfUser(id);
	}

	@GetMapping("/getfavoriterestaurants")
	public DataResult<List<FavoriteRestaurants>> getFavoriteRestaurantsByUserId(@RequestParam Long id) {
		return this.userService.getFavoriteRestaurants(id);
	}

	@GetMapping("/getuseradresses")
	public DataResult<List<Address>> getUserAdresses(@RequestParam Long id) {
		return this.userService.getUserAdresses(id);
	}

	@PostMapping("/addcard")
	public Result addCardInformation(@Valid @RequestParam String endDate,
								   @Valid @RequestParam String ccv, @Valid @RequestParam String cardNumber,
								   @Valid @RequestParam String cardName, @RequestParam Long userId) {
		return this.userService.addCard(endDate, ccv, cardNumber, cardName, userId);
	}
	@PutMapping("/passwordChange")
	public Result passwordChange(@RequestParam Long userId,@RequestParam String oldPassword,
								 @RequestParam String newPassword){
		return this.userService.passwordChange(userId,oldPassword,newPassword);
	}
	@PutMapping("/nameChange")
	public Result nameChange(@RequestParam Long userId,@RequestParam String newName){
		return this.userService.nameChange(userId, newName);
	}
	@PutMapping("/surnameChange")
	public Result surnameChange(@RequestParam Long userId,@RequestParam String newSurname){
		return this.userService.surnameChange(userId,newSurname);
	}
	@GetMapping("/getuserbyid")
	public DataResult<User> getUserById(@RequestParam Long id){
		return this.userService.getUserById(id);
	}
	@DeleteMapping("/delete")
	public Result delete(@RequestParam Long id){
		return this.userService.delete(id);
	}
	@DeleteMapping("/deleteCard")
	public Result deleteCard(@RequestParam Long userId,@RequestParam Long cardId){
		return this.userService.deleteCard(userId,cardId);
	}
	@DeleteMapping("/deleteAddress")
	public Result deleteAddress(@RequestParam Long userId,@RequestParam Long addressId){
		return this.userService.deleteAddress(userId,addressId);
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