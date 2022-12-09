package com.example.foodcommercial.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foodcommercial.business.abstracts.IOrderService;
import com.example.foodcommercial.entities.Order;
import com.example.foodcommercial.repositories.OrderRepository;

@Service
public class OrderService implements IOrderService {

	private OrderRepository orderRepo;
	
	@Autowired
	public OrderService(OrderRepository orderRepo) {
		this.orderRepo=orderRepo;
	}
	
	
	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return this.orderRepo.findAll();
	}

	
	@Override
	public void giveOrder(Order order) {
		// TODO Auto-generated method stub	
		this.orderRepo.save(order);
	}
}
