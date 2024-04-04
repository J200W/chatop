package com.chatop.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * La classe Message est utilisée pour stocker les messages
 */

@Data
@Entity
@Table(name = "message")
@NoArgsConstructor
@Schema(name = "Message", description = "La classe Message est utilisée pour stocker les messages")
public class Message {

    /**
     * L'identifiant du message
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Le contenu du message
     */
    @Column(name = "content")
    private String content;

    /**
     * L'expéditeur du message
     */
    @ManyToOne
    private User sender;

    /**
     * La location associée au message
     */
    @ManyToOne
    private Rental rental;


    public Message(Integer id) {
        this.id = id;
    }
}
