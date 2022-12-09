package com.example.foodcommercial.business.concretes;

import java.util.List;
import java.util.Optional;

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
	
	@Autowired
	public RestaurantService(RestaurantRepository restaurantRepo) {
		// TODO Auto-generated constructor stub
		this.restaurantRepo=restaurantRepo;
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
		// TODO Auto-generated method stub
		return this.restaurantRepo.getReferenceById(id).getEvaluations();
	}
}
