package com.example.foodcommercial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.foodcommercial.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address getAddressById(Long id);
}
