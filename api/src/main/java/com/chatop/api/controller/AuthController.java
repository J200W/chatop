package com.chatop.api.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.model.ERole;
import com.chatop.api.model.Role;
import com.chatop.api.model.User;
import com.chatop.api.payload.request.LoginRequest;
import com.chatop.api.payload.request.RegisterRequest;
import com.chatop.api.payload.response.JwtResponse;
import com.chatop.api.payload.response.MessageResponse;
import com.chatop.api.repository.RoleRepository;
import com.chatop.api.repository.UserRepository;
import com.chatop.api.security.jwt.JwtUtils;
import com.chatop.api.security.service.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    /*
     * AuthenticationManager est utilisé pour l'authentification de l'utilisateur
     */
    @Autowired
    AuthenticationManager authenticationManager;

    /*
     * UserRepository est utilisé pour accéder aux utilisateur de la base de données
     */
    @Autowired
    UserRepository userRepository;

    /*
     * RoleRepository est utilisé pour accéder aux roles de la base de données
     */
    @Autowired
    RoleRepository roleRepository;

    /*
     * PasswordEncoder est utilisé pour encoder le mot de passe de l'utilisateur
     */
    @Autowired
    PasswordEncoder encoder;

    /*
     * JwtUtils est utilisé pour générer un token JWT
     */
    @Autowired
    JwtUtils jwtUtils;

    /*
     * Cette méthode est utilisée pour authentifier un utilisateur
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        // Authentification de l'utilisateur avec l'email et le mot de passe
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        // Mettre l'authentification dans le contexte de sécurité de Spring
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        // Récupérer les détails de l'utilisateur authentifié
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal(); // cast ?

        // Récupérer les roles de l'utilisateur
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        // Retourner le token JWT et les détails de l'utilisateur
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getCreated_at(),
                userDetails.getUpdated_at(),
                roles));
    }

    /*
     * Cette méthode est utilisée pour obtenir les informations de l'utilsateur
     * courant
     */
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {

        // Récupérer les informations de l'utilisateur courant
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        // Récupérer les roles de l'utilisateur
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        // Retourner les informations de l'utilisateur courant
        return ResponseEntity.ok(new JwtResponse(null,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getCreated_at(),
                userDetails.getUpdated_at(),
                roles));
    }

    /*
     * Cette méthode est utilisée pour enregistrer un nouvel utilisateur
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest signUpRequest) {

        // Vérifier si l'email est déjà utilisé
        if (userRepository.existsByEmail(signUpRequest.getEmail())) { // COMMENT existsByEmail marche alors qu'il n'est
                                                                      // pas implémenté ?
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Créer un nouvel utilisateur
        User user = new User(signUpRequest.getName(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));


        user.setCreated_at(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        user.setUpdated_at(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        // Récupérer les roles de l'utilisateur et les ajouter à l'utilisateur
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        // Si les roles ne sont pas spécifiés, attribuer le role USER par défaut
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }

        // Sinon, attribuer les roles spécifiés
        else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "owner":
                        Role ownerRole = roleRepository.findByName(ERole.ROLE_OWNER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(ownerRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER).get();
                        if (userRole == null) {
                            throw new RuntimeException("Error: Role is not found.");
                        }
                        roles.add(userRole);
                }
            });
        }

        // Enregistrer l'utilisateur dans la base de données avec les roles
        user.setRole(roles);
        userRepository.save(user);

        // Authentification de l'utilisateur avec l'email et le mot de passe
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signUpRequest.getEmail(), signUpRequest.getPassword()));

        // Mettre l'authentification dans le contexte de sécurité de Spring
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        // Récupérer les détails de l'utilisateur authentifié
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal(); // cast ?

        // Retourner un message de succès
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getCreated_at(),
                userDetails.getUpdated_at(),
                roles.stream().map(item -> item.getName().toString()).collect(Collectors.toList())));
    }
}
