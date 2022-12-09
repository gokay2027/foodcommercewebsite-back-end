package com.example.foodcommercial.repositories;



import org.springframework.data.jpa.repository.JpaRepository;


import com.example.foodcommercial.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	
	
}
