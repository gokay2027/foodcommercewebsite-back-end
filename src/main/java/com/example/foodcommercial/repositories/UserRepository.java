package com.example.foodcommercial.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.example.foodcommercial.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	
	@Query("SELECT u FROM User u WHERE u.email = ?1 and u.password = ?2")
	Optional<User> loggedUser(String email,String password);
	
	
	
	

}
