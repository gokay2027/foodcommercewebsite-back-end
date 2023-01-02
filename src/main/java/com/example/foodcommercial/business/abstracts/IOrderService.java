package com.example.foodcommercial.business.abstracts;

import java.util.List;

import com.example.foodcommercial.core.utilities.results.DataResult;
import com.example.foodcommercial.core.utilities.results.Result;
import com.example.foodcommercial.entities.Order;

public interface IOrderService {
	
	DataResult<List<Order>> getAllOrdersByUserId(Long userId);
	Result giveOrder(Long foodId, Long paymentId, Long userId, Long userAdressId);
	
}
