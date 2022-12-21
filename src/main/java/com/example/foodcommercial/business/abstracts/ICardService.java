package com.example.foodcommercial.business.abstracts;

import com.example.foodcommercial.entities.CardInformation;

public interface ICardService {

	void addCard(String endDate, String ccv, String cardNumber, String cardName, Long userId);
	
}
