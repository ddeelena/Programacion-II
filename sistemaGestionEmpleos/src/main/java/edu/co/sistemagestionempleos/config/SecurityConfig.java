
package edu.co.sistemagestionempleos.config;

// Importamos las herramientas necesarias para la configuración de seguridad
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

// Indicamos que esta es una clase de configuración para Spring
@Configuration
// Habilitamos la seguridad en la aplicación
@EnableWebSecurity
// Genera automáticamente un constructor con los atributos marcados como "final"
@RequiredArgsConstructor
public class SecurityConfig {

    // Servicio para manejar tokens JWT
    private final JwtService jwtService;

    // Servicio para cargar los detalles de los usuarios (nombre, roles, permisos)
    private final UserDetailsService userDetailsService;

    // Servicio para codificar y verificar contraseñas
    private final PasswordEncoder passwordEncoder;

    // Definimos un "filtro" que se encargará de validar los tokens JWT en cada solicitud
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtService, userDetailsService);
    }

    // Configuración de seguridad de la aplicación
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilitamos la protección CSRF (recomendada cuando usamos JWT en APIs)
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Arrays.asList("http://127.0.0.1:5500"));
                    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
                    config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
                    config.setAllowCredentials(true);
                    return config;
                }))
                .csrf(csrf -> csrf.disable())

                // Configuramos las rutas y permisos de acceso
                .authorizeHttpRequests(auth -> auth
                        // Permitir acceso libre a las rutas de autenticación (/api/auth/**)
                        .requestMatchers("/api/auth/**").permitAll()

                        // Permitir solo a los administradores acceder a las rutas de administración (/api/admin/**)
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")

                        .requestMatchers("/api/public/**").hasAnyRole("USER","ADMIN","CANDIDATO","EMPRESA")

                        .requestMatchers("/api/candidatos/**").hasAnyRole("CANDIDATO","ADMIN")

                        .requestMatchers("/api/empresas/**").hasAnyRole("EMPRESA","ADMIN")

                        .requestMatchers("/api/postulaciones/**").hasAnyRole("CANDIDATO","ADMIN","EMPRESA")

                        // Todas las demás rutas requieren autenticación
                        .anyRequest().authenticated()
                )

                // Configuración de manejo de sesiones
                .sessionManagement(session -> session
                        // No usaremos sesiones, cada petición requiere autenticación (porque usamos JWT)
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Configuramos el proveedor de autenticación
                .authenticationProvider(authenticationProvider())

                // Agregamos el filtro de autenticación JWT antes del filtro de usuario y contraseña
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        // Construimos la configuración y la devolvemos
        return http.build();
    }

    // Configuración del proveedor de autenticación
    @Bean
    public AuthenticationProvider authenticationProvider() {
        // Usamos un proveedor basado en la autenticación de usuarios en la base de datos
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        // Indicamos dónde obtener la información de los usuarios
        authProvider.setUserDetailsService(userDetailsService);

        // Indicamos cómo se van a codificar las contraseñas para compararlas al iniciar sesión
        authProvider.setPasswordEncoder(passwordEncoder);

        return authProvider;
    }

    // Configuración del administrador de autenticación
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }



}
