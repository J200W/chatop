package com.chatop.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "message")

/**
 * The message class
 */
public class Message {

    /**
     * The id of the message
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The content of the message
     */
    @Column(name = "content")
    private String content;

    /**
     * The sender id of the message
     */
    @Column(name = "sender_id")
    private int senderId;

    /**
     * The receiver id of the message
     */
    @Column(name = "receiver_id")
    private int receiverId;
    
}
