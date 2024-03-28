package com.chatop.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
     * Get user by id
     * 
     * @param id the id of the user
     * @return the user
     */
    @GetMapping("/user/{id}")
    public Optional<User> getUserByIdController(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

}
