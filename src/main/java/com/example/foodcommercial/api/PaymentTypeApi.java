package com.example.foodcommercial.api;

import com.example.foodcommercial.business.abstracts.IPaymentTypeService;
import com.example.foodcommercial.core.utilities.results.DataResult;
import com.example.foodcommercial.core.utilities.results.Result;
import com.example.foodcommercial.entities.PaymentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/paymentType")
public class PaymentTypeApi {
    private IPaymentTypeService paymentTypeService;

    @Autowired
    public PaymentTypeApi(IPaymentTypeService paymentTypeService) {
        this.paymentTypeService = paymentTypeService;
    }
    @PostMapping("/add")
    public Result add(@RequestParam String name){
        return this.paymentTypeService.add(name);
    }
    @GetMapping("/getAll")
    public DataResult<List<PaymentType>> getAllPaymentTypes(){
        return this.paymentTypeService.getAllPaymentTypes();
    }
}
