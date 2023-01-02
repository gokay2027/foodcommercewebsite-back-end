package com.example.foodcommercial.business.abstracts;

import com.example.foodcommercial.core.utilities.results.DataResult;
import com.example.foodcommercial.core.utilities.results.Result;
import com.example.foodcommercial.entities.Category;
import com.example.foodcommercial.entities.Food;
import com.example.foodcommercial.entities.Restaurant;

import java.util.List;

public interface ICategoryService {
    Result add(String name);
    DataResult<List<Category>> getAllCategories();
    Result delete(Long id);
}
