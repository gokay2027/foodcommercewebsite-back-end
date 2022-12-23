package com.example.foodcommercial.business.abstracts;

import com.example.foodcommercial.core.utilities.results.DataResult;
import com.example.foodcommercial.core.utilities.results.Result;
import com.example.foodcommercial.entities.PaymentType;

import java.util.List;

public interface IPaymentTypeService {
    Result add(String name);
    DataResult<List<PaymentType>> getAllPaymentTypes();
}
