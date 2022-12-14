package com.example.foodcommercial.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foodcommercial.business.abstracts.IUserService;
import com.example.foodcommercial.entities.Adress;
import com.example.foodcommercial.entities.CardInformation;
import com.example.foodcommercial.entities.FavoriteRestaurants;
import com.example.foodcommercial.entities.User;
import com.example.foodcommercial.repositories.AdressRepository;
import com.example.foodcommercial.repositories.UserRepository;

@Service
public class UserService implements IUserService {

	private UserRepository userRepo;
	private AdressRepository adressRepo;

	@Autowired
	public UserService(UserRepository userRepo, AdressRepository adressRepo) {

		this.userRepo = userRepo;
		this.adressRepo = adressRepo;

	}

	@Override
	public Optional<User> loginUser(String email, String password) {
		// TODO Auto-generated method stub

		if (this.userRepo.loginUser(email, password) != null) {
			return this.userRepo.loginUser(email, password);
		} else {

			return null;
		}
	}

	@Override
	public Optional<User> registerUser(String name, String surname, String email, String password, String phoneNumber, String birthDate) {
		// TODO Auto-generated method stub
		User user = new User(null,name,surname,email,password,phoneNumber,birthDate,null,null,null);
		userRepo.save(user);
		return this.userRepo.loginUser(email, password);
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

	@Override
	public List<FavoriteRestaurants> getFavoriteRestaurants(Long id) {
		// TODO Auto-generated method stub
		return this.userRepo.findById(id).get().getFavoriteRestaurants();
	}

	@Override
	public List<Adress> getUserAdresses(Long id) {
		// TODO Auto-generated method stub
		return this.userRepo.findById(id).get().getAdress();
	}
	
	
	
	
}
