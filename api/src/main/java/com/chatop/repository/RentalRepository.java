package com.chatop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chatop.model.Rental;

/**
 * The rental repository
 */
@Repository
public interface RentalRepository extends CrudRepository<Rental, Long>{

}
