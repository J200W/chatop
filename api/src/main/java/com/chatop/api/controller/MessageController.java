package com.chatop.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.model.Message;
import com.chatop.api.service.MessageService;

/**
 * The message controller
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * Add message
     * 
     * @param message - The message to add
     */
    @PostMapping("/messages")
    public ResponseEntity<String> addMessageController(@RequestBody Message message) {
        messageService.addMessage(message);
        return ResponseEntity.ok("Message added successfully");
    }
}
