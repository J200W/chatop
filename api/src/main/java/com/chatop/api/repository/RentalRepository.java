package com.chatop.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chatop.api.model.Rental;

/**
 * The rental repository
 */
@Repository
public interface RentalRepository extends CrudRepository<Rental, Integer>{
    
}
