package com.chatop.api.service;

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
    
}
