package com.example.foodcommercial.business.concretes;

import java.util.List;

import com.example.foodcommercial.core.utilities.results.*;
import com.example.foodcommercial.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foodcommercial.business.abstracts.IOrderService;
import com.example.foodcommercial.repositories.AddressRepository;
import com.example.foodcommercial.repositories.FoodRepository;
import com.example.foodcommercial.repositories.OrderRepository;
import com.example.foodcommercial.repositories.PaymentTypeRepository;

import com.example.foodcommercial.repositories.UserRepository;

@Service
public class OrderService implements IOrderService {

	private OrderRepository orderRepo;
	private FoodRepository foodRepo;
	private PaymentTypeRepository paymentTypeRepo;
	private UserRepository userRepo;

	private AddressRepository addressRepo;

	@Autowired
	public OrderService(OrderRepository orderRepo, FoodRepository foodRepo, PaymentTypeRepository paymentTypeRepo,
			UserRepository userRepo, AddressRepository addressRepo) {

		this.orderRepo = orderRepo;
		this.foodRepo = foodRepo;
		this.paymentTypeRepo = paymentTypeRepo;
		this.userRepo = userRepo;
		this.addressRepo = addressRepo;
	}

	@Override
	public DataResult<List<Order>> getAllOrdersByUserId(Long userId) {
		User user = this.userRepo.getUserById(userId);
		if(user!=null){
			return new SuccessDataResult<List<Order>>(this.orderRepo.getOrdersByUser(user), "Orders");
		}
		else return new ErrorDataResult<>("Error!");
	}

	@Override
	public Result giveOrder(Long foodId, Long paymentId, Long userId, Long userAdressId) {
		Food food = this.foodRepo.getFoodById(foodId);
		PaymentType paymentType = this.paymentTypeRepo.getPaymentTypeById(paymentId);
		User user = this.userRepo.getUserById(userId);
		Address address = this.addressRepo.getAddressById(userAdressId);
		boolean flag = false;
		if(user!=null) {
			for (Address address1 : user.getAddresses()) {
				if (address1 == address) {
					flag = true;
					break;
				} else {
					flag = false;
				}
			}
		}
		if(food!=null && paymentType!=null && user!=null && address!=null && flag){
			Order order = new Order(null, food, paymentType,
					user, foodRepo.getFoodById(foodId).getRestaurant(),
					address);

			this.orderRepo.save(order);
			return new SuccessResult("Give Order Successful!");
		}
		else return new ErrorResult("Error!");
	}
}
