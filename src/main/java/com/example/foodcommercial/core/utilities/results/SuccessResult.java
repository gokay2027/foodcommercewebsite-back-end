package com.example.foodcommercial.core.utilities.results;

import lombok.Getter;

@Getter
public class SuccessResult extends Result{

    public SuccessResult() {
        super(true);
    }

    public SuccessResult(String message) {
        super(true, message);
    }
}
