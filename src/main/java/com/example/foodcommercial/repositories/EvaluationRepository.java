package com.example.foodcommercial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.foodcommercial.entities.Evaluation;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
	
}
