package com.chatop.api.controller;

import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.model.User;
import com.chatop.api.service.UserService;

/**
 * Le contrôleur UserController est utilisé pour gérer les utilisateurs
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Obtenir l'utilisateur par son identifiant
     *
     * @param id est l'identifiant de l'utilisateur
     * @return the user
     */
    @GetMapping("/user/{id}")
    @Operation(
        summary = "Obtenir l'utilisateur par son identifiant",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")},
            responseCode = "200",
            description = "Obtenir l'utilisateur par son identifiant"
        ),
    })
    public Optional<User> getUserByIdController(@PathVariable("id") Integer id) {
        try {
            return userService.getUserById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
