package com.chatop.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatop.api.model.Message;
import com.chatop.api.service.MessageService;

import java.util.HashMap;
import java.util.Map;

/**
 * Le contrôleur MessageController est utilisé pour gérer les messages
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * Ajouter un message
     *
     * @param message - Le message à ajouter
     */
    @PostMapping("/messages")
    @Operation(
        summary = "Ajouter un message",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Message ajouté avec succès"),
        @ApiResponse(responseCode = "500", description = "Erreur lors de l'envoi du message")
    })
    public ResponseEntity<Object> addMessageController(@RequestBody Message message) {
        try{
            System.out.println("Message: " + message.getContent());
            messageService.addMessage(message);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Message sent successfully");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<Object>("Erreur lors de l'envoi du message", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
