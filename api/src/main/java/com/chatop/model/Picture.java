package com.chatop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private Long id;

    /**
     * The url of the picture
     */
    @Column(name = "url")
    private String url;

    /**
     * The rental id of the picture
     */
    @Column(name = "rental_id")
    private int rentalId;

}
