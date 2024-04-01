package com.chatop.api.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

import com.chatop.api.repository.UserRepository;
import com.chatop.api.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatop.api.model.Rental;
import com.chatop.api.service.RentalService;
import org.springframework.web.multipart.MultipartFile;

/**
 * Le contrôleur RentalController est utilisé pour gérer les locations
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserRepository userRepository;

    /**
     * Le chemin du répertoire de téléchargement d'images
     */
    @Value("${store.rootDir}")
    private String rootDir;


    @Value("${store.uploadDir}")
    private String uploadDir;

    @Value("${store.localPath}")
    private String localPath;

    /**
     * Obtenir toutes les locations
     *
     * @return - Toutes les locations
     */
    @GetMapping("/rentals")
    public ResponseEntity<List<Rental>> getAllRentalsController() {
        List<Rental> rentals = rentalService.getAllRentals();
        return ResponseEntity.ok(rentals);
    }

    /**
     * Obtenir une location par son identifiant
     *
     * @param id - L'identifiant de la location
     * @return - La location
     */
    @GetMapping("/rentals/{id}")
    public ResponseEntity<Rental> getRentalByIdController(@PathVariable("id") Integer id) {
        Optional<Rental> rental = rentalService.getRentalById(id);
        return rental.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Ajourter une location
     *
     * @param picture     - La photo du bien
     * @param name        - Le nom du bien
     * @param price       - Le prix du bien
     * @param description - La description du bien
     * @param surface     - La surface du bien
     * @param owner       - L'identifiant du propriétaire du bien
     * @return un message de confirmation
     */
    @PostMapping(path = "/rentals", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public @ResponseBody ResponseEntity<Object>
    addRentalController(
        @RequestPart("name") String name,
        @RequestPart("surface") String surface,
        @RequestPart("price") String price,
        @RequestPart("picture") MultipartFile picture,
        @RequestPart("description") String description,
        @RequestPart("owner") String owner)
    {

        // extraire le nom de l'image
        String filename = Objects.requireNonNull(picture.getOriginalFilename())
            .split("\\.")[0];

        // extraire l'extension de l'image
        String extension = "." + Objects.requireNonNull(picture.getOriginalFilename())
            .split("\\.")[1];

        // générer un identifiant aléatoire
        String uniqueID = UUID.randomUUID().toString();

        // chemin complet de l'image pour le stockage
        String fullPath = rootDir + uploadDir + filename + uniqueID + extension;

        // chemin local de l'image pour la base de données
        String localPathImg = localPath + filename + uniqueID + extension;
        try {
            // create the directory to store the image
            Path path = Paths.get(rootDir + uploadDir);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            // sauvegarder l'image
            Files.write(Paths.get(fullPath), picture.getBytes());

            // sauvegarder l'image dans la base de données
            Rental rental = new Rental();
            rental.setName(name);
            rental.setSurface(Double.parseDouble(surface));
            rental.setPrice(Double.parseDouble(price));
            rental.setDescription(description);
            rental.setPicture(localPathImg);

            rental.setOwner(this.userRepository.
                findById(Integer.parseInt(owner))
                .orElseGet(null));
            rental.setCreated_at(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date()));
            rental.setUpdated_at(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date()));
            rentalService.addRental(rental);

        } catch (IOException e) {
            // Effacer l'image si une erreur se produit
            try {
                Files.deleteIfExists(Paths.get(fullPath));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            e.printStackTrace();
        }

        // Initialiser la réponse
        Map<String, String> response = new HashMap<>();
        response.put("message", "Rental added successfully");
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    /**
     * Mettre à jour une location
     * @param name        - Le nom du bien
     * @param price       - Le prix du bien
     * @param description - La description du bien
     * @param surface     - La surface du bien
     * @return un message de confirmation
     */
    @PutMapping(path = "/rentals/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public @ResponseBody ResponseEntity<Object>
    updateRentalController(
        @PathVariable("id") Integer id,
        @RequestPart("name") String name,
        @RequestPart("surface") String surface,
        @RequestPart("price") String price,
        @RequestPart("description") String description)
    {
        try {
            // sauvegarder l'image
            Rental rental = rentalService.getRentalById(id).orElseGet(null);
            rental.setName(name);
            rental.setSurface(Double.parseDouble(surface));
            rental.setPrice(Double.parseDouble(price));
            rental.setDescription(description);
            rental.setUpdated_at(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date()));
            rentalService.updateRental(rental);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> response = new HashMap<>();
        response.put("message", "Rental updated successfully");
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    /**
     * Supprimer une location
     *
     * @param id - L'identifiant de la location
     * @return un message de confirmation
     */

    @DeleteMapping("/rentals/{id}")
    public ResponseEntity<Object> deleteRentalController(@PathVariable("id") Integer id) {
        // delete all the messages related to the rental
        messageService.deleteAllMessagesOfRental(id);

        // delete the rental
        rentalService.deleteRental(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Rental deleted successfully");
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}
