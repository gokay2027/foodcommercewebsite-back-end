package com.example.foodcommercial.business.abstracts;

import java.util.List;

import com.example.foodcommercial.entities.Order;

public interface IOrderService {
	
	List<Order> getAllOrders();
	
	void giveOrder(Order order);
	
}
