package com.example.foodcommercial.business.concretes;

import com.example.foodcommercial.business.abstracts.IPortionService;
import com.example.foodcommercial.core.utilities.results.ErrorResult;
import com.example.foodcommercial.core.utilities.results.Result;
import com.example.foodcommercial.core.utilities.results.SuccessResult;
import com.example.foodcommercial.entities.Portion;
import com.example.foodcommercial.repositories.PortionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortionService implements IPortionService {
    PortionRepository portionRepo;

    @Autowired
    public PortionService(PortionRepository portionRepo) {
        this.portionRepo = portionRepo;
    }

    @Override
    public Result add(String name) {
        Portion portion = new Portion(null, name);
        this.portionRepo.save(portion);
        return new SuccessResult("Add Portion Successful!");
    }
}
