package com.example.foodcommercial.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodcommercial.business.concretes.CardService;
import com.example.foodcommercial.entities.CardInformation;


@RestController
@RequestMapping("/cardinformation")
public class CardInformationApi {
	
	private CardService cardService;

	@Autowired
	public CardInformationApi(CardService cardService) {
		this.cardService=cardService;
	}
	
	
	@PutMapping("/addcard/{id}")
	public void addCardInformation(@PathVariable(value="id") Long id,@RequestBody CardInformation card) {
		this.cardService.addCard(id, card);
	}
	
	
}
