package com.chatop.api.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@NoArgsConstructor

/**
 * La classe User est utilisée pour stocker les informations de l'utilisateur
 */

public class User {

    /**
     * L'identifiant de l'utilisateur
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Le nom de l'utilisateur
     */
    @Column(name = "name")
    private String name;

    /**
     * L'email de l'utilisateur
     */
    @Column(name = "email")
    private String email;

    /**
     * Le mot de passe de l'utilisateur
     */
    @Column(name = "password")
    private String password;

    /*
     * La date de création de l'utilisateur
     */

    @Column(name = "created_at")
    private String created_at;

    /*
     * La date de mise à jour de l'utilisateur
     */
    @Column(name = "updated_at")
    private String updated_at;

    /**
     * Les rôles de l'utilisateur
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> role = new HashSet<>();

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(Integer id) {
        this.id = id;
    }
}
