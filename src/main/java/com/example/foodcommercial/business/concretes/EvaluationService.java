package com.example.foodcommercial.business.concretes;



import com.example.foodcommercial.core.utilities.results.ErrorResult;
import com.example.foodcommercial.core.utilities.results.Result;
import com.example.foodcommercial.core.utilities.results.SuccessResult;
import com.example.foodcommercial.entities.Restaurant;
import com.example.foodcommercial.entities.User;
import com.example.foodcommercial.repositories.RestaurantRepository;
import com.example.foodcommercial.repositories.UserRepository;
import org.springframework.stereotype.Service;

import com.example.foodcommercial.business.abstracts.IEvaluationService;
import com.example.foodcommercial.entities.Evaluation;
import com.example.foodcommercial.repositories.EvaluationRepository;

@Service
public class EvaluationService implements IEvaluationService {
	
	private EvaluationRepository evaluationRepo;
	private UserRepository userRepository;
	private RestaurantRepository restaurantRepository;

	public EvaluationService(EvaluationRepository evaluationRepo,
							 UserRepository userRepository,
							 RestaurantRepository restaurantRepository) {
		
		this.evaluationRepo=evaluationRepo;
		this.userRepository=userRepository;
		this.restaurantRepository=restaurantRepository;
	}

	@Override
	public Result addEvaluation(String content, int rateValue, Long restaurantId, Long userId) {
		User user = this.userRepository.getUserById(userId);
		Restaurant restaurant = this.restaurantRepository.getRestaurantById(restaurantId);
		Evaluation evaluation = new Evaluation(null, content ,rateValue, user, restaurant);
		if(user != null && restaurant != null) {
			user.getEvaluations().add(evaluation);
			this.userRepository.save(user);
			restaurant.getEvaluations().add(evaluation);
			this.restaurantRepository.save(restaurant);
			this.evaluationRepo.save(evaluation);
			return new SuccessResult("Save Successful!");
		}
		else return new ErrorResult("Error!");
	}
	
}
