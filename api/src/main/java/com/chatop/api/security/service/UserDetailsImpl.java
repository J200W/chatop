package com.chatop.api.security.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.chatop.api.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * UserDetailsImpl est utilisé pour implémenter UserDetails et pour charger les
 * informations de l'utilisateur
 */
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    /*
     * id de l'utilisateur
     */
    private Integer id;

    /*
     * email de l'utilisateur
     */
    private String email;

    /*
     * nom de l'utilisateur
     */
    private String name;

    /*
     * mot de passe de l'utilisateur
     */
    @JsonIgnore
    private String password;

    /*
     * date de création de l'utilisateur
     */
    private String created_at;

    /*
     * date de mise à jour de l'utilisateur
     */
    private String updated_at;

    /*
     * les roles de l'utilisateur
     */
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * Constructor
     * 
     * @param id
     * @param name
     * @param email
     * @param password
     * @param created_at
     * @param updated_at
     * @param authorities
     */

    public UserDetailsImpl(Integer id, String name, String email, String password, String created_at, String updated_at,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.authorities = authorities;
    }

    /**
     * build qui permet de construire un UserDetailsImpl à partir d'un utilisateur
     * 
     * @param user
     * @return
     */
    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRole().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getCreated_at(),
                user.getUpdated_at(),
                authorities);
    }

    /*
     * Cette méthode retourne les roles de l'utilisateur
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
