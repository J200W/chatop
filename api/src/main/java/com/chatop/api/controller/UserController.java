package com.chatop.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.model.User;
import com.chatop.api.service.UserService;

/**
 * The user controller
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Get user by id
     * 
     * @param id est l'identifiant de l'utilisateur
     * @return the user
     */
    @GetMapping("/user/{id}")
    public Optional<User> getUserByIdController(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

}
