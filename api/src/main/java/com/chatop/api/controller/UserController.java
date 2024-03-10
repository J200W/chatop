package com.chatop.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.model.User;
import com.chatop.api.service.UserService;

/**
 * The user controller
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Get all users
     * 
     * @return - All users
     */

    @GetMapping("/users/:id")
    public Optional<User> getUserById(int id) {
        return userService.getUserById(id);
    }
}
