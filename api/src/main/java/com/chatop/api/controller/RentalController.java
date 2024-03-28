package com.chatop.api.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatop.api.model.Rental;
import com.chatop.api.service.RentalService;
import org.springframework.web.multipart.MultipartFile;

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
     * Le chemin du répertoire de téléchargement d'images
     */
    @Value("${store.rootDir}")
    private String rootDir;


    @Value("${store.uploadDir}")
    private String uploadDir;

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
        return rental.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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
     */
    @PutMapping(path="/rentals/{id}", consumes={ MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> updateRentalController(@RequestPart("picture") MultipartFile picture,
                                                         @RequestPart("rental") Rental rental,
                                                         @PathVariable("id") Integer id)
    {
        try {
            final Path picturePath = Paths.get(uploadDir);
            final Path pictureFilePath = Paths.get(rootDir + uploadDir + picture.getOriginalFilename());

            if (!picturePath.toFile().exists()) {
                Files.createDirectories(picturePath);
            }

            Files.write(pictureFilePath, picture.getBytes());

//            rentalService.updateRental();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("Rental updated");
    }

}
