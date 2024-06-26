package com.chatop.api.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.api.model.Rental;
import com.chatop.api.repository.RentalRepository;

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
     *
     * @return all rentals
     */
    public List<Rental> getAllRentals() {
        // Retrieve all rentals from the repository
        List<Rental> allRentals = rentalRepository.findAll();

        return allRentals.stream()
            .sorted(Comparator.comparing(Rental::getCreated_at).reversed())
            .collect(Collectors.toList());
    }

    /**
     * Get rental by id
     *
     * @param id the id of the rental
     * @return the rental
     */
    public Optional<Rental> getRentalById(Integer id) {
        return rentalRepository.findById(id);
    }

    /**
     * Add rental
     *
     * @param rental the rental to add
     */
    public void addRental(Rental rental) {
        rentalRepository.save(rental);
    }

    /**
     * Update rental
     *
     * @param rental the rental to update
     */
    public void updateRental(Rental rental) {
        rentalRepository.save(rental);
    }

    /**
     * Delete rental
     *
     * @param id the id of the rental to delete
     */
    public void deleteRental(Integer id) {
        rentalRepository.deleteById(id);
    }
}
