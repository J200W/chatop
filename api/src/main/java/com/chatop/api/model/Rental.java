package com.chatop.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
/**
 * The rental class
 */

@Data
@Entity
@Table(name = "rental")

public class Rental {

    /**
     * The id of the rental
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The name of the rental
     */
    @Column(name = "name")
    private String name;

    /**
     * The description of the rental
     */
    private String description;

    /**
     * The price of the rental
     */
    private double price;

    /**
     * The surface of the rental
     */
    private double surface;

    /**
     * The number of rooms of the rental
     */
    @Column(name = "owner_id")
    private int ownerId;

}
