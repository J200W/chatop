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
@Table(name = "user")

/**
 * The user class
 */

public class User {

    /**
     * The id of the user
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private int role;

}
