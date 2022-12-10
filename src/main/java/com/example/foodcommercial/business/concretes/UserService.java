package com.example.foodcommercial.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foodcommercial.business.abstracts.IUserService;
import com.example.foodcommercial.entities.Adress;
import com.example.foodcommercial.entities.CardInformation;
import com.example.foodcommercial.entities.User;
import com.example.foodcommercial.repositories.AdressRepository;
import com.example.foodcommercial.repositories.CardInformationRepository;
import com.example.foodcommercial.repositories.UserRepository;

@Service
public class UserService implements IUserService {

	private UserRepository userRepo;
	private AdressRepository adressRepo;
	



	@Autowired
	public UserService(UserRepository userRepo,AdressRepository adressRepo) {

		System.out.println("Selam2 baran");
		this.userRepo = userRepo;
		this.adressRepo=adressRepo;
		

	}
	
	@Override
	public Optional<User> loginUser(String email, String password) {
		// TODO Auto-generated method stub

		if (this.userRepo.loggedUser(email, password) != null) {
			return this.userRepo.loggedUser(email, password);
		} else {

			return null;
		}
	}

	@Override
	public void registerUser(User user) {
		// TODO Auto-generated method stub
		this.userRepo.save(user);

	}

	

	@Override
	public void addUserAdress(Long id, Adress adress) {
		// TODO Auto-generated method stub
		
		User user = this.userRepo.getReferenceById(id);
		
		this.adressRepo.save(adress);
		
		user.getAdress().add(adress);
		
		this.userRepo.save(user);
		
		
	}

	@Override
	public List<CardInformation> getCardsOfUser(Long id) {
		// TODO Auto-generated method stub
		return this.userRepo.findById(id).get().getCards();
	}

}
