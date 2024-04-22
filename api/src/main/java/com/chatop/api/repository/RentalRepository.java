package com.chatop.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatop.api.model.Rental;

/**
 * Le repository RentalRepository est utilisé pour gérer les locations
 */
@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

}
