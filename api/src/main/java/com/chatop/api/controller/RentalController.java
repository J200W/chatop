package com.chatop.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.model.Rental;
import com.chatop.api.service.RentalService;

/**
 * The rental controller
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    /**
     * Get all rentals
     * 
     * @return - All rentals
     */
    @GetMapping("/rentals")
    public ResponseEntity<List<Rental>> getAllRentalsController() {
        List<Rental> rentals = rentalService.getAllRentals();
        return ResponseEntity.ok(rentals);
    }

    /**
     * Get rental by id
     * 
     * @param id - The id of the rental
     * @return - The rental
     */
    @GetMapping("/rentals/{id}")
    public ResponseEntity<Rental> getRentalByIdController(@PathVariable("id") Integer id) {
        Optional<Rental> rental = rentalService.getRentalById(id);
        if (rental.isPresent()) {
            return ResponseEntity.ok(rental.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Add rental
     * 
     * @param rental - The rental to add
     */
    @PostMapping("/rentals")
    public ResponseEntity<String> addRentalController(@RequestBody Rental rental) {

        rentalService.addRental(rental);
        return ResponseEntity.ok("Rental added");
    }

    /**
     * Update rental
     * 
     * @param rental - The rental to update
     */
    @PutMapping("/rentals")
    public ResponseEntity<String> updateRentalController(@RequestBody Rental rental) {
        rentalService.updateRental(rental);
        return ResponseEntity.ok("Rental updated");
    }

}
