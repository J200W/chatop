package com.chatop.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chatop.api.model.Message;
import com.chatop.api.service.MessageService;

import java.util.HashMap;
import java.util.Map;

/**
 * The message controller
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * Add message
     *
     * @param message - The message to add
     */
    @PostMapping("/messages")
    public ResponseEntity<Object> addMessageController(@RequestBody Message message) {
        System.out.println("Message: " + message.getContent());
        messageService.addMessage(message);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Message added successfully");
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}
