package com.example.foodcommercial.repositories;



import com.example.foodcommercial.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


import com.example.foodcommercial.entities.Order;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o_ FROM User u_ JOIN Order o_ on u_.id=o_.user.id WHERE u_ = :user")
    List<Order> getOrdersByUser(User user);
}
