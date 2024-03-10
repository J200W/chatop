package com.chatop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.model.Rental;
import com.chatop.repository.RentalRepository;

import lombok.Data;

/**
 * The rental service
 */
@Data
@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    /**
     * Get all rentals
     * @return all rentals
     */
    public Iterable<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    /**
     * Get rental by id
     * @param id the id of the rental
     * @return the rental
     */
    public Optional<Rental> getRentalById(Long id) {
        return rentalRepository.findById(id);
    }

    /**
     * Add rental
     * @param rental the rental to add
     */
    public void addRental(Rental rental) {
        rentalRepository.save(rental);
    }

    /**
     * Update rental
     * @param rental the rental to update
     */
    public void updateRental(Rental rental) {
        rentalRepository.save(rental);
    }

    /**
     * Delete rental
     * @param id the id of the rental to delete
     */
    public void deleteRental(Long id) {
        rentalRepository.deleteById(id);
    }
}
