package com.example.foodcommercial.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodcommercial.business.concretes.UserService;
import com.example.foodcommercial.entities.Adress;
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
	public Optional<User> logginUser(@PathVariable(value = "email") String email,
			@PathVariable(value = "password") String password) {

		return this.userService.loginUser(email, password);

	}

	@GetMapping("/getcards/{id}")
	public List<CardInformation> getCardsOfUser(@PathVariable(value = "id") Long id) {
		return this.userService.getCardsOfUser(id);
	}

	@GetMapping("/getfavoriterestaurants/{id}")
	public List<FavoriteRestaurants> getFavoriteRestaurantsByUserId(@PathVariable(value = "id") Long id) {
		return this.userService.getFavoriteRestaurants(id);
	}

	@GetMapping("/getuseradresses")
	public List<Adress> getUserAdresses(@RequestParam Long id) {
		return this.userService.getUserAdresses(id);
	}

	// User register
	@PostMapping(value = "/register")
	public Optional<User> registerUser(@RequestParam String name, @RequestParam String surname, @RequestParam String email,
			@RequestParam String password, @RequestParam String phoneNumber, @RequestParam String birthDate) {
		// Email unique olduğu için aynı emaildern user girerse
		// hata alır
		return this.userService.registerUser(name, surname, email, password, phoneNumber, birthDate);
	}

	// User adds adress
	@PutMapping(value = "/addadress/{id}")
	public void addUserAdress(@PathVariable(value = "id") Long id, @RequestBody Adress adress) {
		this.userService.addUserAdress(id, adress);
	}

}
