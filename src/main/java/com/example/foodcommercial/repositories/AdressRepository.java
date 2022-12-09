package com.example.foodcommercial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.foodcommercial.entities.Adress;

public interface AdressRepository extends JpaRepository<Adress, Long> {

}
