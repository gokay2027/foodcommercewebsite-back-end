package com.example.foodcommercial.business.concretes;

import com.example.foodcommercial.business.abstracts.ICategoryService;
import com.example.foodcommercial.core.utilities.results.*;
import com.example.foodcommercial.entities.Category;
import com.example.foodcommercial.entities.Food;
import com.example.foodcommercial.entities.Restaurant;
import com.example.foodcommercial.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    private CategoryRepository categoryRepo;

    @Autowired
    public CategoryService(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Result add(String name) {
        Category category = new Category(null,name,null);
        this.categoryRepo.save(category);
        return new SuccessResult("Add Category Successful!");
    }

    @Override
    public DataResult<List<Category>> getAllCategories() {
        return new SuccessDataResult<>(this.categoryRepo.findAll());
    }

    @Override
    public Result delete(Long id) {
        Category category = this.categoryRepo.getCategoryById(id);
        if(category!=null){
            this.categoryRepo.delete(category);
            return new SuccessResult("Deleted Successfully!");
        }
        else return new ErrorResult("Error!");
    }


}
