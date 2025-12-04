package com.riwi.events_management.infrastructure.security.controllers;

import com.riwi.events_management.application.dto.request.auth.LoginRequest;
import com.riwi.events_management.application.dto.request.auth.RegisterRequest;
import com.riwi.events_management.infrastructure.security.jwt.JwtUtil;
import com.riwi.events_management.infrastructure.security.user.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AppUserRepository repository;
    private final RoleRepository roleRepository;
    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(AppUserRepository repository,
                          RoleRepository roleRepository,
                          UserDetailsServiceImpl userDetailsService,
                          PasswordEncoder passwordEncoder,
                          JwtUtil jwtUtil) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // ==========================
    // Registro normal
    // ==========================
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("ROLE_USER no existe en DB"));

        AppUser user = new AppUser();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of(userRole));

        repository.save(user);

        return "Usuario registrado correctamente";
    }

    // ==========================
    // Login
    // ==========================
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        AppUser user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        // 🔥 Cargar UserDetails (con roles incluidos)
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

        // 🔥 Generar token con username + roles
        return jwtUtil.generateToken(userDetails);
    }

    // ==========================
    // CREAR ADMIN
    // ==========================
    @PostMapping("/create-admin")
    public String createAdmin(@RequestBody RegisterRequest request) {

        if (repository.findByUsername(request.getUsername()).isPresent()) {
            return "El usuario ya existe.";
        }

        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("ROLE_ADMIN no existe en DB"));

        AppUser admin = new AppUser();
        admin.setUsername(request.getUsername());
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin.setRoles(Set.of(adminRole));

        repository.save(admin);

        return "Administrador creado correctamente.";
    }
}
