package com.example.foodcommercial.business.concretes;

import com.example.foodcommercial.business.abstracts.IPaymentTypeService;
import com.example.foodcommercial.core.utilities.results.DataResult;
import com.example.foodcommercial.core.utilities.results.Result;
import com.example.foodcommercial.core.utilities.results.SuccessDataResult;
import com.example.foodcommercial.core.utilities.results.SuccessResult;
import com.example.foodcommercial.entities.PaymentType;
import com.example.foodcommercial.repositories.PaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentTypeService implements IPaymentTypeService {
    private PaymentTypeRepository paymentTypeRepo;

    @Autowired
    public PaymentTypeService(PaymentTypeRepository paymentTypeRepo) {
        this.paymentTypeRepo = paymentTypeRepo;
    }

    @Override
    public Result add(String name) {
        PaymentType paymentType = new PaymentType(null,name);
        this.paymentTypeRepo.save(paymentType);
        return new SuccessResult("Added Successfully!");
    }

    @Override
    public DataResult<List<PaymentType>> getAllPaymentTypes() {
        return new SuccessDataResult<>(this.paymentTypeRepo.findAll());
    }
}
