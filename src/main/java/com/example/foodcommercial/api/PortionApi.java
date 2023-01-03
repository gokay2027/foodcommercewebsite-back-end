package com.example.foodcommercial.api;

import com.example.foodcommercial.business.abstracts.IPortionService;
import com.example.foodcommercial.business.concretes.PortionService;
import com.example.foodcommercial.core.utilities.results.ErrorDataResult;
import com.example.foodcommercial.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/portion")
public class PortionApi {
    IPortionService portionService;

    @Autowired
    public PortionApi(PortionService portionService) {
        this.portionService = portionService;
    }

    @PostMapping("/addPortion")
    public Result add(@RequestParam String name){
        return portionService.add(name);
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
