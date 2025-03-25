package edu.co.sistemagestionempleos.service.impl;

import edu.co.sistemagestionempleos.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager; // Maneja la autenticación
    private final JwtService jwtService; // Servicio para manejar los tokens JWT
    private final UserDetailsService userDetailsService; // Carga los detalles del usuario

    // Método que maneja el proceso de login y genera un token JWT
    public String login(String username, String password) {
        // Verifica las credenciales del usuario (Spring Security lo hace internamente)
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        // Carga los detalles del usuario desde la base de datos
        UserDetails user = userDetailsService.loadUserByUsername(username);

        // Genera un token JWT para el usuario autenticado y lo devuelve
        return jwtService.generateToken(user);
    }
}
