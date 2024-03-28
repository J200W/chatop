package com.chatop.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.chatop.api.security.jwt.AuthEntryPointJwt;
import com.chatop.api.security.jwt.AuthTokenFilter;
import com.chatop.api.security.service.UserDetailsServiceImpl;

/*
 * Cette classe est utilisée pour configurer la sécurité de l'application
 */
@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler; // Gestionnaire des erreurs d'authentification

    // Créer un filtre pour valider les jetons JWT
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    /*
     * Cette méthode est utilisée pour créer un fournisseur d'authentification
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        // Créer un fournisseur d'authentification
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        // Définir le service UserDetailsServiceImpl pour le fournisseur
        // d'authentification
        authProvider.setUserDetailsService(userDetailsService);

        // Définir le mot de passe encoder pour le fournisseur d'authentification
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    /*
     * Cette méthode est utilisée pour créer un gestionnaire d'authentification
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /*
     * Cette méthode est utilisée pour encoder le mot de passe de l'utilisateur
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * Cette méthode est utilisée pour configurer les règles de sécurité
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Désactiver la protection CSRF
        http.csrf(csrf -> csrf.disable())
                
                // Personnaliser la réponse pour les erreurs d'authentification
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                
                // Désactiver la gestion de session
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Autoriser les requêtes pour les chemins d'authentification
                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/**").permitAll()
                        
                        // Autoriser les requêtes pour les chemins de test
                        .requestMatchers("/api/test/**").permitAll()
                        
                        // Autoriser les requêtes pour les autres chemins de l'API
                        .anyRequest().authenticated());

        // Ajouter un filtre pour valider les jetons JWT
        http.authenticationProvider(authenticationProvider());

        // Ajouter un filtre pour valider les jetons JWT
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        // Retourner la configuration de sécurité
        return http.build();
    }
}
