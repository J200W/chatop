package com.chatop.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatop.api.model.Message;

/**
 * The message repository
 */

public interface MessageRepository extends JpaRepository<Message, Integer> {

}
