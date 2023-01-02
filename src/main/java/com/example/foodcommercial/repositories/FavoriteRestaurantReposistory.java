package com.example.foodcommercial.repositories;

import com.example.foodcommercial.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.foodcommercial.entities.FavoriteRestaurants;

import java.util.List;

public interface FavoriteRestaurantReposistory extends JpaRepository<FavoriteRestaurants, Long> {
    List<FavoriteRestaurants> getFavoriteRestaurantsByUser(User user);

}
