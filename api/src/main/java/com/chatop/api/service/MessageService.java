package com.chatop.api.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.api.model.Message;
import com.chatop.api.repository.MessageRepository;

import lombok.Data;

/**
 * The message service
 */

@Data
@Service
@Transactional
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    /**
     * Add message
     *
     * @param message the message to add
     */
    public void addMessage(Message message) {
        messageRepository.save(message);
    }

    /**
     * delete all messages of a rental
     */
    @Transactional
    public void deleteAllMessagesOfRental(Integer rentalId) {
        messageRepository.deleteAllByRentalId(rentalId);
    }

}
