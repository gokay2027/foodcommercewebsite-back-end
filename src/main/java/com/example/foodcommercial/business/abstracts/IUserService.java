package com.example.foodcommercial.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.foodcommercial.entities.Adress;
import com.example.foodcommercial.entities.CardInformation;
import com.example.foodcommercial.entities.FavoriteRestaurants;
import com.example.foodcommercial.entities.User;

public interface IUserService {
	
	Optional<User> loginUser(String email,String password);
	
	void registerUser(User user);
	
	void addUserAdress(Long id,Adress adress);
	
	List<CardInformation> getCardsOfUser(Long id); 
	
	List<FavoriteRestaurants> getFavoriteRestaurants(Long id); 
	
	
	
}
