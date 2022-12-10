package com.example.foodcommercial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.foodcommercial.entities.FavoriteRestaurants;

public interface FavoriteRestaurantReposistory extends JpaRepository<FavoriteRestaurants, Long> {

}
