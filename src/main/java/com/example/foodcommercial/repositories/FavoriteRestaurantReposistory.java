package com.example.foodcommercial.repositories;

import com.example.foodcommercial.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.foodcommercial.entities.FavoriteRestaurants;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteRestaurantReposistory extends JpaRepository<FavoriteRestaurants, Long> {
    @Query("SELECT f_ FROM User u_ JOIN FavoriteRestaurants f_ on u_.id=f_.user.id WHERE u_ = :user")
    List<FavoriteRestaurants> getFavoriteRestaurantsByUser(User user);
    FavoriteRestaurants getFavoriteRestaurantsById(Long id);

}
