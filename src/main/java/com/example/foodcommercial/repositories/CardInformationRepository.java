package com.example.foodcommercial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.foodcommercial.entities.CardInformation;

public interface CardInformationRepository extends JpaRepository<CardInformation, Long> {
    CardInformation getCardInformationById(Long id);
    CardInformation deleteCardInformationById(Long id);
    void deleteById(Long id);

}
