package com.example.foodcommercial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.foodcommercial.entities.Portion;

public interface PortionRepository extends JpaRepository<Portion, Long> {

	Portion getPortionById(Long id);
	
}
