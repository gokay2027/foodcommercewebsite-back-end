package com.example.foodcommercial.business.concretes;

import java.util.List;

import com.example.foodcommercial.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foodcommercial.business.abstracts.IUserService;
import com.example.foodcommercial.entities.Address;
import com.example.foodcommercial.entities.CardInformation;
import com.example.foodcommercial.entities.FavoriteRestaurants;
import com.example.foodcommercial.entities.User;
import com.example.foodcommercial.repositories.AddressRepository;
import com.example.foodcommercial.repositories.UserRepository;

@Service
public class UserService implements IUserService {

	private UserRepository userRepo;
	private AddressRepository adressRepo;

	@Autowired
	public UserService(UserRepository userRepo, AddressRepository adressRepo) {

		this.userRepo = userRepo;
		this.adressRepo = adressRepo;

	}

	@Override
	public DataResult<User> loginUser(String email, String password) {

		if (this.userRepo.loginUser(email, password) != null) {
			return new SuccessDataResult<User>(this.userRepo.loginUser(email, password),
					"Login Successful!");
		} else {
			return new ErrorDataResult<>("Login Failed!");
		}
	}

	@Override
	public Result registerUser(String name, String surname, String email, String password, String phoneNumber, String birthDate) {
		User user = new User(null,name,surname,email,password,phoneNumber,birthDate,null,null,null,null);
		userRepo.save(user);
		return new SuccessResult("Registration Successful!");
	}

	@Override
	public Result addUserAdress(Long id, Address address) {
		User user = this.userRepo.getReferenceById(id);
		this.adressRepo.save(address);
		user.getAddresses().add(address);
		this.userRepo.save(user);
		return new SuccessResult("Add Address Successful!");
	}

	@Override
	public DataResult<List<CardInformation>> getCardsOfUser(Long id) {
		return new SuccessDataResult<List<CardInformation>>
				(this.userRepo.getReferenceById(id).getCards());
	}

	@Override
	public DataResult<List<FavoriteRestaurants>> getFavoriteRestaurants(Long id) {
		return new SuccessDataResult<List<FavoriteRestaurants>>
				(this.userRepo.getReferenceById(id).getFavoriteRestaurants());
	}

	@Override
	public DataResult<List<Address>> getUserAdresses(Long id) {
		return new SuccessDataResult<List<Address>>
				(this.userRepo.getReferenceById(id).getAddresses());
	}
	
	
	
	
}
