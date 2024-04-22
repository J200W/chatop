package com.chatop.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatop.api.model.Message;

/**
 * Le repository MessageRepository est utilisé pour gérer les messages
 */

public interface MessageRepository extends JpaRepository<Message, Integer> {

    /**
     * Delete all messages of a rental
     *
     * @param rentalId the id of the rental
     */
    void deleteAllByRentalId(Integer rentalId);
}
