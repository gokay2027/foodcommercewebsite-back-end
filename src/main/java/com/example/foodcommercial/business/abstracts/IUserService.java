package com.example.foodcommercial.business.abstracts;

import java.util.Optional;

import com.example.foodcommercial.entities.Adress;
import com.example.foodcommercial.entities.User;

public interface IUserService {
	
	Optional<User> loginUser(String email,String password);
	
	void registerUser(User user);
	
	void addUserAdress(Long id,Adress adress);
}
