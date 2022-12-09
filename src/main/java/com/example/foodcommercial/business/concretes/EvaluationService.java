package com.example.foodcommercial.business.concretes;



import org.springframework.stereotype.Service;

import com.example.foodcommercial.business.abstracts.IEvaluationService;
import com.example.foodcommercial.entities.Evaluation;
import com.example.foodcommercial.repositories.EvaluationRepository;

@Service
public class EvaluationService implements IEvaluationService {
	
	private EvaluationRepository evaluationRepo;
	
	
	
	
	public EvaluationService(EvaluationRepository evaluationRepo) {
		
		this.evaluationRepo = evaluationRepo;
		
	}
	

	@Override
	public void addEvaluation(Evaluation evaluation) {
		// TODO Auto-generated method stub
		this.evaluationRepo.save(evaluation);
	}
	
}
