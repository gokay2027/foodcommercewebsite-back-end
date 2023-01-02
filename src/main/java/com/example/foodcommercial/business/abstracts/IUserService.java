package com.example.foodcommercial.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.foodcommercial.core.utilities.results.DataResult;
import com.example.foodcommercial.core.utilities.results.Result;
import com.example.foodcommercial.entities.Address;
import com.example.foodcommercial.entities.CardInformation;
import com.example.foodcommercial.entities.FavoriteRestaurants;
import com.example.foodcommercial.entities.User;

public interface IUserService {
	
	DataResult<User> loginUser(String email, String password);
	
	Result registerUser(String name, String surname, String email, String password, String phoneNumber, String birthDate);
	
	Result addUserAdress(Long id, String streetNo,
						 String hoodName, String buildingNumber, String district, String city);
	
	DataResult<List<CardInformation>> getCardsOfUser(Long id);
	
	DataResult<List<FavoriteRestaurants>> getFavoriteRestaurants(Long id);

	DataResult<List<Address>> getUserAdresses(Long id);

	Result addCard(String endDate, String ccv, String cardNumber, String cardName, Long userId);

	Result passwordChange(Long userId, String oldPassword, String newPassword);
	Result nameChange(Long userId, String newName);
	Result surnameChange(Long userId, String newSurname);
	DataResult<User> getUserById(Long userId);
	Result delete(Long id);
	Result deleteCard(Long userId, Long cardId);
	Result deleteAddress(Long userId, Long addressId);
	
}
