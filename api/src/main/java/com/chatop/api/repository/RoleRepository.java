package com.chatop.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatop.api.model.ERole;
import com.chatop.api.model.Role;

/**
 * Le repository RoleRepository est utilisé pour accéder aux roles de la base de données
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
