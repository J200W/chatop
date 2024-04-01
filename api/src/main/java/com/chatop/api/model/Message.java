package com.chatop.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "message")
@NoArgsConstructor

/**
 * The message class
 */
public class Message {

    /**
     * The id of the message
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The content of the message
     */
    @Column(name = "content")
    private String content;

    /**
     * The sender id of the message
     */
    @ManyToOne
    private User sender;

    /**
     * The rental id of the message
     */
    @ManyToOne
    private Rental rental;


    public Message(Integer id) {
        this.id = id;
    }
}
