package com.ChallengeBackEndJava.controller;

import com.ChallengeBackEndJava.security.JwtUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
    String username = request.get("username");
    String password = request.get("password");

    // Imprimir valores de entrada
    System.out.println("Intentando autenticar: ");
    System.out.println("Username: " + username);
    System.out.println("Password (ingresada): " + password);

    try {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        // Imprimir información del usuario autenticado
        System.out.println("Autenticación exitosa para el usuario: " + username);

        String token = jwtUtil.generateToken(username);
        return ResponseEntity.ok(Map.of("token", token));
    } catch (Exception e) {
        // Imprimir mensaje de error si falla la autenticación
        System.out.println("Error durante la autenticación: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos..." + username+"..." + password);
    }
}

}
