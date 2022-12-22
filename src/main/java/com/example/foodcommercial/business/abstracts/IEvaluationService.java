package com.example.foodcommercial.business.abstracts;


import com.example.foodcommercial.core.utilities.results.Result;
import com.example.foodcommercial.entities.Evaluation;

public interface IEvaluationService {
	
	
	
	Result addEvaluation(String content, int rateValue, Long restaurantId, Long userId);
	
}
