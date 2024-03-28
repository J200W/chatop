package com.chatop.api.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chatop.api.model.User;
import com.chatop.api.repository.UserRepository;

/**
 * Le service UserDetailsServiceImpl est utilisé pour charger les détails de
 * l'utilisateur
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /*
     * UserRepository est utilisé pour accéder aux utilisateur de la base de données
     */
    @Autowired
    UserRepository userRepository;

    /**
     * Cette méthode est utilisée pour charger les informations de l'utilisateur à
     * partir de la base de données lors de l'authentification
     * 
     * @param username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + username));

        return UserDetailsImpl.build(user);
    }
}
