package com.chatop.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "picture")

/**
 * The picture class
 */
public class Picture {

    /**
     * The id of the picture
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The url of the picture
     */
    @Column(name = "url")
    private String url;

    /**
     * The rental id of the picture
     */
    @OneToOne
    private Rental rental;

}
