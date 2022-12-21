package com.example.foodcommercial.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.example.foodcommercial.business.concretes.CardService;
import com.example.foodcommercial.entities.CardInformation;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/cardinformation")
public class CardInformationApi {
	
	private CardService cardService;

	@Autowired
	public CardInformationApi(CardService cardService) {
		this.cardService=cardService;
	}
	
	
	@PostMapping("/addcard")
	public void addCardInformation(@Valid @RequestParam String endDate,
								   @Valid @RequestParam String ccv, @Valid @RequestParam String cardNumber,
								   @Valid @RequestParam String cardName, @RequestParam Long userId) {
		this.cardService.addCard(endDate, ccv, cardNumber, cardName, userId);
	}

	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleValidationException(ValidationException exceptions) {

		return exceptions.getMessage();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Collection<String> handleValidationException(MethodArgumentNotValidException exceptions) {
		Map<String,String> validationErrors = new HashMap<String,String>();
		for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}

		return validationErrors.values();
	}
}
