package com.chatop.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user")
@NoArgsConstructor

/**
 * The user class
 */

public class User {

    /**
     * The id of the user
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The name of the user
     */
    @Column(name = "name")
    private String name;

    /**
     * The email of the user
     */
    private String password;

    /**
     * The role of the user
     */
    @Column(name = "role")
    private Integer role;

    public User(Integer id) {
        this.id = id;
    }
}
