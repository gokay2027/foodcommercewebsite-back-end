package com.example.foodcommercial.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foodcommercial.business.abstracts.IFoodService;
import com.example.foodcommercial.entities.Food;
import com.example.foodcommercial.repositories.FoodRepository;

@Service
public class FoodService implements IFoodService {

	private FoodRepository foodRepo;
	
	@Autowired
	public FoodService(FoodRepository foodRepo) {
		this.foodRepo=foodRepo;
	}
	
	
	@Override
	public List<Food> getAllFood() {
		// TODO Auto-generated method stub
		return this.foodRepo.findAll();
	}


	


	
}
