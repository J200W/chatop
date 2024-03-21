package com.chatop.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "role")
/**
 * The role class
 */
public class Role {

    /**
     * The id of the role
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The name of the role
     */

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    /**
     * The default constructor of the role
     */

    public Role() {}

    /**
     * The constructor of the role
     * @param name The name of the role
     */
    public Role(ERole name) {
        this.name = name;
    }

}
