package edu.co.sistemagestionempleos.controller;

import edu.co.sistemagestionempleos.Auth.LoginRequest;
import edu.co.sistemagestionempleos.Auth.LoginResponse;
import edu.co.sistemagestionempleos.Auth.RegisterRequest;
import edu.co.sistemagestionempleos.config.JwtService;
import edu.co.sistemagestionempleos.model.User;
import edu.co.sistemagestionempleos.service.UserService;
import edu.co.sistemagestionempleos.service.impl.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/api/auth") // Define la URL base para las solicitudes relacionadas con autenticación
@RequiredArgsConstructor // Lombok genera un constructor con los atributos final
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;

/*    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        User user = userService.registerUser(request.getUsername(), request.getPassword(), request.getRole());
        System.out.println(user.getRole());
        String token = jwtService.generateToken(user);
        System.out.println(token);
        return ResponseEntity.ok(token);
    }*/

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest request) {
        User user = userService.registerUser(request.getUsername(), request.getPassword(), request.getRole());
        System.out.println(user.getRole());
        String token = jwtService.generateToken(user);
        System.out.println(token);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        UserDetails user = userService.loadUserByUsername(request.getUsername());
        String token = jwtService.generateToken(user);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/id/{username}")
    public ResponseEntity<Integer> getUserId(@PathVariable String username) {
        Integer userId = userService.getUserIdByUsername(username);
        return ResponseEntity.ok(userId);
    }

    @GetMapping("/admin/test")
    public ResponseEntity<String> adminTest() {
        return ResponseEntity.ok("Admin access granted");
    }
}
