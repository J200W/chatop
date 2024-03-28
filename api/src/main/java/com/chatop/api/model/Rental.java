package com.chatop.api.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The rental class
 */

@Data
@Entity
@Table(name = "rental")
@NoArgsConstructor
@AllArgsConstructor

public class Rental {

    /**
     * The id of the rental
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The name of the rental
     */
    @Column(name = "name")
    private String name;

    /**
     * The description of the rental
     */
    @Column(name = "description")
    private String description;

    /**
     * The price of the rental
     */
    @Column(name = "price")
    private double price;

    /**
     * The surface of the rental
     */
    @Column(name = "surface")
    private double surface;

    /**
     * The owner id of the rental
     */
    @ManyToOne
    private User owner;

    public Rental(Integer id) {
        this.id = id;
    }
}
