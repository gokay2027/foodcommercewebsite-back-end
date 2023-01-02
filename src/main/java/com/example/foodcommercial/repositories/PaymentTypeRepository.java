package com.example.foodcommercial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.foodcommercial.entities.PaymentType;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {
    PaymentType getPaymentTypeById(Long id);
}
