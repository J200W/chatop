package com.chatop.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Le contrôleur TestController est utilisé pour tester les autorisations
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    @PreAuthorize("permitAll()")
    @Operation(summary = "TEST - Accès public")
    @ApiResponses(value = {
        @ApiResponse(
            description = "Public Content.",
            responseCode = "200"
        )
    })
    public ResponseEntity<Object> allAccess() {
        return ResponseEntity.ok("Public Content.");
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('OWNER') or hasRole('ADMIN')")
    @Operation(
        summary = "TEST - Accès utilisateur",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            description = "User Content.",
            responseCode = "200"
        ),
        @ApiResponse(
            description = "Erreur d'accès",
            responseCode = "403"
        )
    })
    public ResponseEntity<Object> userAccess() {
        return ResponseEntity.ok("User Content.");
    }

    @GetMapping("/owner")
    @PreAuthorize("hasRole('OWNER')")
    @Operation(
        summary = "TEST - Accès propriétaire",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            description = "Owner Board.",
            content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")},
            responseCode = "200"
        ),
        @ApiResponse(
            description = "Erreur d'accès",
            content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")},
            responseCode = "403"
        )
    })
    public ResponseEntity<Object> ownerAccess() {
        return ResponseEntity.ok("Owner Board.");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
        summary = "TEST - Accès administrateur",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")},
            description = "Admin Board.",
            responseCode = "200"
        ),
        @ApiResponse(
            content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")},
            description = "Erreur d'accès",
            responseCode = "403"
        )
    })
    public ResponseEntity<Object> adminAccess() {
        return ResponseEntity.ok("Admin Board.");
    }
}
