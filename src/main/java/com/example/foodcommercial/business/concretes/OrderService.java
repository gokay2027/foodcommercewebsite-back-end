package com.example.foodcommercial.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foodcommercial.business.abstracts.IOrderService;
import com.example.foodcommercial.entities.Order;
import com.example.foodcommercial.repositories.AdressRepository;
import com.example.foodcommercial.repositories.FoodRepository;
import com.example.foodcommercial.repositories.OrderRepository;
import com.example.foodcommercial.repositories.PaymentTypeRepository;
import com.example.foodcommercial.repositories.RestaurantRepository;
import com.example.foodcommercial.repositories.UserRepository;

@Service
public class OrderService implements IOrderService {

	private OrderRepository orderRepo;
	private FoodRepository foodRepo;
	private PaymentTypeRepository paymentTypeRepo;
	private UserRepository userRepo;
	private RestaurantRepository restaurantRepo;
	private AdressRepository addressRepo;

	@Autowired
	public OrderService(OrderRepository orderRepo, FoodRepository foodRepo, PaymentTypeRepository paymentTypeRepo,
			UserRepository userRepo, RestaurantRepository restaurantRepo, AdressRepository addressRepo) {

		this.orderRepo = orderRepo;
		this.foodRepo = foodRepo;
		this.paymentTypeRepo = paymentTypeRepo;
		this.userRepo = userRepo;
		this.restaurantRepo = restaurantRepo;
		this.addressRepo = addressRepo;
	}

	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return this.orderRepo.findAll();
	}

	@Override
	public void giveOrder(Long foodId, Long paymentId, Long userId, Long userAdressId) {
		// TODO Auto-generated method stub

		Order order = new Order(null, foodRepo.findById(foodId).get(), paymentTypeRepo.findById(paymentId).get(),
				userRepo.findById(userId).get(), foodRepo.findById(foodId).get().getRestaurant(),
				addressRepo.findById(userAdressId).get());

		this.orderRepo.save(order);
	}
}
