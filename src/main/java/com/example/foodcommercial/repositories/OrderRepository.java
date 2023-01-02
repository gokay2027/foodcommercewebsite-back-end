package com.example.foodcommercial.repositories;



import com.example.foodcommercial.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


import com.example.foodcommercial.entities.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
    List<Order> getOrdersByUser(User user);
}
