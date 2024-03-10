package com.chatop.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.model.Rental;
import com.chatop.service.RentalService;

/**
 * The rental controller
 */
@RestController
public class RentalController {

    @Autowired
    private RentalService rentalService;

    /**
     * Get all rentals
     * 
     * @return - All rentals
     */
    @GetMapping("/rentals")
    public Iterable<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }

    /**
     * Get rental by id
     * 
     * @param id - The id of the rental
     * @return - The rental
     */
    @GetMapping("/rentals/:id")
    public Optional<Rental> getRentalById(Long id) {
        return rentalService.getRentalById(id);
    }

    /**
     * Add rental
     * 
     * @param rental - The rental to add
     */
    @PostMapping("/rentals")
    public void addRental(Rental rental) {
        rentalService.addRental(rental);
    }

    /**
     * Update rental
     * 
     * @param rental - The rental to update
     */
    @PutMapping("/rentals")
    public void updateRental(Rental rental) {
        rentalService.updateRental(rental);
    }

}
