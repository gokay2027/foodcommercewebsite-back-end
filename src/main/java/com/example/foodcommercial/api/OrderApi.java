package com.example.foodcommercial.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.foodcommercial.core.utilities.results.DataResult;
import com.example.foodcommercial.core.utilities.results.ErrorDataResult;
import com.example.foodcommercial.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.example.foodcommercial.business.abstracts.IOrderService;
import com.example.foodcommercial.entities.Order;

import javax.validation.ValidationException;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderApi {
	
	private IOrderService orderService;
	
	@Autowired
	public OrderApi(IOrderService orderService) {
		this.orderService=orderService;
	}
	
	
	@GetMapping(value="/allorders")
	public DataResult<List<Order>> getAllOrdersByUserId(@RequestParam Long userId){
		return this.orderService.getAllOrdersByUserId(userId);
	}
	
	@PostMapping(value = "/neworder")
	public Result addOrder(@RequestParam Long foodId,
						   @RequestParam Long paymentId,
						   @RequestParam Long userId,
						   @RequestParam Long userAdressId) {
		
		return this.orderService.giveOrder(foodId,paymentId,userId,userAdressId);
	}

	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(ValidationException exceptions) {

		ErrorDataResult<Object> errors =
				new ErrorDataResult<Object>(exceptions.getMessage(), "Validation Errors!");
		return errors;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
		Map<String,String> validationErrors = new HashMap<String,String>();
		for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		ErrorDataResult<Object> errors =
				new ErrorDataResult<Object>(validationErrors, "Validation Errors!");
		return errors;
	}
	
}
