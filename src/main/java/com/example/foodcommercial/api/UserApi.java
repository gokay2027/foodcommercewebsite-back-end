package com.example.foodcommercial.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodcommercial.business.concretes.UserService;
import com.example.foodcommercial.entities.Adress;
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
	@GetMapping
	@RequestMapping("/{email}/{password}")
	public Optional<User> logginUser(@PathVariable(value = "email") String email,
			@PathVariable(value = "password") String password) {

		if (this.userService.loginUser(email, password) == null) {

			return null;

		} else {
			return this.userService.loginUser(email, password);
		}
	}

	// User register
	@PostMapping(value = "/register")
	public void registerUser(@RequestBody User user) {
		// Email unique olduğu için aynı emaildern user girerse
		// hata alır
		this.userService.registerUser(user);
	}

	// User adds adress
	@PutMapping(value = "/addadress/{id}")
	public void addUserAdress(@PathVariable(value = "id") Long id, @RequestBody Adress adress) {
		this.userService.addUserAdress(id, adress);
	}

}
