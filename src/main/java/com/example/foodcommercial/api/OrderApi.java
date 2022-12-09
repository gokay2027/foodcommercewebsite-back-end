package com.example.foodcommercial.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodcommercial.business.abstracts.IOrderService;
import com.example.foodcommercial.entities.Order;


@RestController
@RequestMapping("/order")
public class OrderApi {
	
	private IOrderService orderService;
	
	@Autowired
	public OrderApi(IOrderService orderService) {
		this.orderService=orderService;
	}
	
	
	@GetMapping(value="/allorders")
	public List<Order> getAllOrders(){
		return this.orderService.getAllOrders();
	}
	
	@PostMapping(value = "/neworder")
	public void addOrder(@RequestBody Order order) {
		this.orderService.giveOrder(order);
	}
	
}
