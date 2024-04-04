package com.chatop.api.model;


import io.swagger.v3.oas.annotations.media.Schema;
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
 * La classe Rental représente une location
 */

@Data
@Entity
@Table(name = "rental")
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Rental", description = "La classe Rental est utilisée pour stocker les locations")
public class Rental {

    /**
     * L'identifiant de la location
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Le nom de la location
     */
    @Column(name = "name")
    private String name;

    /**
     * La description de la location
     */
    @Column(name = "description")
    private String description;

    /**
     * Le prix de la location
     */
    @Column(name = "price")
    private double price;

    /**
     * La surface de la location
     */
    @Column(name = "surface")
    private double surface;

    /*
     * La photo de la location
     */
    @Column(name = "picture")
    private String picture;

    /*
     * La date de création de la location
     */
    @Column(name = "created_at")
    private String created_at;

    /*
     * La date de mise à jour de la location
     */
    @Column(name = "updated_at")
    private String updated_at;

    /**
     * Le propriétaire de la location
     */
    @ManyToOne
    private User owner;

    public Rental(Integer id) {
        this.id = id;
    }
}
