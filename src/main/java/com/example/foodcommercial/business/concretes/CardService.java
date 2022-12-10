package com.example.foodcommercial.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foodcommercial.business.abstracts.ICardService;
import com.example.foodcommercial.entities.CardInformation;
import com.example.foodcommercial.repositories.CardInformationRepository;
import com.example.foodcommercial.repositories.UserRepository;

@Service
public class CardService implements ICardService {
	
	private CardInformationRepository cardInfoRepo;
	private UserRepository userRepo;
	
	@Autowired
	public CardService(CardInformationRepository cardInfoRepo,UserRepository userRepo){
		this.cardInfoRepo=cardInfoRepo;
		this.userRepo=userRepo;
	}

	@Override
	public void addCard(Long id, CardInformation cardInfo) {
		cardInfo.setUser(userRepo.getReferenceById(id));
		this.cardInfoRepo.save(cardInfo);
	}
	
	
	

}
