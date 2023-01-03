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

import com.example.foodcommercial.business.abstracts.IFoodService;
import com.example.foodcommercial.entities.Food;

import javax.validation.Valid;
import javax.validation.ValidationException;

@CrossOrigin
@RestController
@RequestMapping("/food")
public class FoodApi {

	private IFoodService foodService;

	@Autowired
	public FoodApi(IFoodService foodService) {
		this.foodService = foodService;
	}

	@GetMapping("/getall")
	public DataResult<List<Food>> getAllFood() {
		return this.foodService.getAllFood();
	}

	@PostMapping("/add")
	public Result add(@Valid @RequestParam String name, @RequestParam int price,
					  @RequestParam Long categoryId, @RequestParam Long restaurantId){
		return this.foodService.add(name, price, categoryId, restaurantId);
	}

	@PutMapping("/addPortion")
	public Result addPortion(@RequestParam Long foodId, @RequestParam Long portionId){
		return this.foodService.addPortion(foodId,portionId);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestParam Long id){
		return this.foodService.delete(id);
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

	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(ValidationException exceptions) {

		ErrorDataResult<Object> errors =
				new ErrorDataResult<Object>(exceptions.getMessage(), "Validation Errors!");
		return errors;
	}

}
