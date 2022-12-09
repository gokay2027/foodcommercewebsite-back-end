package com.example.foodcommercial.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodcommercial.business.abstracts.IFoodService;
import com.example.foodcommercial.entities.Food;

@RestController
@RequestMapping("/food")
public class FoodApi {

	private IFoodService foodService;

	@Autowired
	public FoodApi(IFoodService foodService) {
		this.foodService = foodService;
	}

	@GetMapping("/getall")
	public List<Food> getAllFood() {
		return this.foodService.getAllFood();
	}

}
