package com.example.foodcommercial.business.concretes;

import java.util.List;
import java.util.Optional;

import com.example.foodcommercial.entities.Address;
import com.example.foodcommercial.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foodcommercial.business.abstracts.IRestaurantService;
import com.example.foodcommercial.entities.Evaluation;
import com.example.foodcommercial.entities.Food;
import com.example.foodcommercial.entities.Restaurant;
import com.example.foodcommercial.repositories.RestaurantRepository;

@Service
public class RestaurantService implements IRestaurantService {
	
	RestaurantRepository restaurantRepo;
	AddressRepository addressRepository;
	
	@Autowired
	public RestaurantService(RestaurantRepository restaurantRepo, AddressRepository addressRepository) {
		this.restaurantRepo=restaurantRepo;
		this.addressRepository=addressRepository;
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		// TODO Auto-generated method stub
		return restaurantRepo.findAll();
	}

	@Override
	public Optional<Restaurant> getRestaurantById(Long id) {
		// TODO Auto-generated method stub
		return restaurantRepo.findById(id);
	}

	@Override
	public List<Food> getFoodListByRestaurantId(Long id) {
		// TODO Auto-generated method stub
		return restaurantRepo.findById(id).get().getFoods();
	}

	@Override
	public List<Evaluation> getEvaluationsByRestaurantId(Long id) {
		return this.restaurantRepo.getReferenceById(id).getEvaluations();
	}

	@Override
	public void add(String name, Long addressId) {
		Address address = addressRepository.getReferenceById(addressId);
		Restaurant restaurant = new Restaurant(null, name, address, null,null,null);
		this.restaurantRepo.save(restaurant);
	}
}
