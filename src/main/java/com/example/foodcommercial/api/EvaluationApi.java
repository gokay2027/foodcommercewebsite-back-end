package com.example.foodcommercial.api;

import com.example.foodcommercial.business.concretes.EvaluationService;
import com.example.foodcommercial.core.utilities.results.Result;
import com.example.foodcommercial.core.utilities.results.SuccessResult;
import com.example.foodcommercial.entities.Evaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/evaluation")
public class EvaluationApi {
    private EvaluationService evaluationService;

    @Autowired
    public EvaluationApi(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping("/add")
    public Result addEvaluation(@RequestParam String content, @RequestParam int rateValue,
                                @RequestParam Long restaurantId,@RequestParam Long userId){
        return this.evaluationService.addEvaluation(content,rateValue,restaurantId,userId);
    }
}
