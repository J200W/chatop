package com.chatop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chatop.model.User;
import com.chatop.repository.UserRepository;

import lombok.Data;

@Data
@Service

/**
 * The user service
 */
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Get all users
     * 
     * @return all users
     */
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Get user by id
     * 
     * @param id the id of the user
     * @return the user
     */
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    /**
     * Add user
     * 
     * @param user the user to add
     */
    public void addUser(User user) {
        userRepository.save(user);
    }

    /**
     * Update user
     * 
     * @param user the user to update
     */
    public void updateUser(User user) {
        userRepository.save(user);
    }

    /**
     * Delete user
     * 
     * @param id the id of the user to delete
     */
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

}
