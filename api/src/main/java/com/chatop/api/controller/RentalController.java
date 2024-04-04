package com.chatop.api.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

import com.chatop.api.repository.UserRepository;
import com.chatop.api.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @Operation(
        summary = "Obtenir toutes les locations",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")},
            responseCode = "200",
            description = "Liste des locations récupérée avec succès"
        ),
        @ApiResponse(
            content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")},
            responseCode = "500",
            description = "Erreur interne du serveur"
        ),
        @ApiResponse(
            content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")},
            responseCode = "401",
            description = "Full authentication is required to access this resource"
        )
    })
    public ResponseEntity<List<Rental>> getAllRentalsController() {
        try {
            List<Rental> rentals = rentalService.getAllRentals();
            return ResponseEntity.ok(rentals);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Obtenir une location par son identifiant
     *
     * @param id - L'identifiant de la location
     * @return - La location
     */
    @GetMapping("/rentals/{id}")
    @Operation(
        summary = "Obtenir une location par son identifiant",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")},
            responseCode = "200",
            description = "Location récupérée avec succès"
        ),
        @ApiResponse(
            content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")},
            responseCode = "404",
            description = "Erreur interne du serveur"
        ),
        @ApiResponse(
            content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")},
            responseCode = "401",
            description = "Full authentication is required to access this resource"
        )
    })
    public ResponseEntity<Rental> getRentalByIdController(@PathVariable("id") Integer id) {
        try {
            Optional<Rental> rental = rentalService.getRentalById(id);
            return rental.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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
    @Operation(
        summary = "Ajouter une location",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")},
            responseCode = "200",
            description = "Location ajoutée avec succès"
        ),
        @ApiResponse(
            content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")},
            responseCode = "500",
            description = "Erreur interne du serveur"
        ),
        @ApiResponse(
            content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")},
            responseCode = "401",
            description = "Full authentication is required to access this resource"
        )
    })
    public @ResponseBody ResponseEntity<Object>
    addRentalController(
        @RequestPart("name") String name,
        @RequestPart("surface") String surface,
        @RequestPart("price") String price,
        @RequestPart("picture") MultipartFile picture,
        @RequestPart("description") String description,
        @RequestPart("owner") String owner) {

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
                findById(Integer.parseInt(owner)).orElseGet(null));
            rental.setCreated_at(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date()));
            rental.setUpdated_at(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date()));
            rentalService.addRental(rental);

            // Initialiser la réponse
            Map<String, String> response = new HashMap<>();
            response.put("message", "Location ajoutée avec succès");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (IOException e) {
            // Effacer l'image si une erreur se produit
            try {
                Files.deleteIfExists(Paths.get(fullPath));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Mettre à jour une location
     *
     * @param name        - Le nom du bien
     * @param price       - Le prix du bien
     * @param description - La description du bien
     * @param surface     - La surface du bien
     * @return un message de confirmation
     */
    @PutMapping(path = "/rentals/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(
        summary = "Mettre à jour une location",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")},
            responseCode = "200",
            description = "Location mise à jour avec succès"
        ),
        @ApiResponse(
            content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")},
            responseCode = "500",
            description = "Erreur interne du serveur"
        ),
        @ApiResponse(
            content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")},
            responseCode = "401",
            description = "Full authentication is required to access this resource"
        )
    })
    public @ResponseBody ResponseEntity<Object>
    updateRentalController(
        @PathVariable("id") Integer id,
        @RequestPart("name") String name,
        @RequestPart("surface") String surface,
        @RequestPart("price") String price,
        @RequestPart("description") String description) {
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
            Map<String, String> response = new HashMap<>();
            response.put("message", "Location mise à jour avec succès");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Supprimer une location
     *
     * @param id - L'identifiant de la location
     * @return un message de confirmation
     */

    @DeleteMapping("/rentals/{id}")
    @Operation(
        summary = "Supprimer une location",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")},
            responseCode = "200",
            description = "Location supprimée avec succès"
        ),
        @ApiResponse(
            content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")},
            responseCode = "500",
            description = "Erreur interne du serveur"
        ),
        @ApiResponse(
            content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")},
            responseCode = "401",
            description = "Full authentication is required to access this resource"
        )
    })
    public ResponseEntity<Object> deleteRentalController(@PathVariable("id") Integer id) {
        try {
            // supprimer les messages de la location
            messageService.deleteAllMessagesOfRental(id);

            // supprimer la location
            rentalService.deleteRental(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Location supprimée avec succès");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
