// Paquete donde se encuentra esta clase dentro del proyecto
package edu.co.sistemagestionempleos.config;

// Importamos las herramientas necesarias para trabajar con filtros en las solicitudes HTTP
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Importamos herramientas de seguridad y autenticación
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

// Indicamos que esta clase es un "componente" de Spring (se detecta automáticamente en la aplicación)
@Component
// Anotación que genera un constructor con los atributos marcados como "final"
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // Servicio que maneja la generación y validación de tokens JWT
    private final JwtService jwtService;

    // Servicio que maneja los detalles de los usuarios (como nombre y permisos)
    private final UserDetailsService userDetailsService;

    // Metodo que se ejecuta automáticamente en cada solicitud HTTP para filtrar las peticiones
    @Override
    protected void doFilterInternal(HttpServletRequest request, // Petición del usuario
                                    HttpServletResponse response, // Respuesta del servidor
                                    FilterChain filterChain) // Cadena de filtros de seguridad
            throws ServletException, IOException {
        // Obtenemos el valor del encabezado "Authorization" de la petición HTTP
        final String authHeader = request.getHeader("Authorization");
        final String jwt; // Aquí guardaremos el token JWT
        final String username; // Aquí guardaremos el nombre de usuario extraído del token

        // Si no hay encabezado "Authorization" o no empieza con "Bearer", pasamos al siguiente filtro y terminamos
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extraemos el token JWT eliminando la palabra "Bearer" (los primeros 7 caracteres)
        jwt = authHeader.substring(7);

        // Extraemos el nombre de usuario del token usando el servicio JWT
        username = jwtService.extractUsername(jwt);

        // Si se obtuvo un nombre de usuario y no hay una autenticación activa en el sistema...
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Cargamos los detalles del usuario desde la base de datos o el sistema de autenticación
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // Si el token es válido para este usuario...
            if (jwtService.isTokenValid(jwt, userDetails)) {

                // Extraemos el rol del token
                String role = jwtService.extractRole(jwt); // Método que extrae "role" del token
                System.out.println("Rol extraido en filter: "+role);
                // Creamos una lista de autoridades basada en el rol del token
                var authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
                System.out.println("lista de autoridades: "+authorities);
                // Creamos un objeto de autenticación con las autoridades del token
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, // Usuario autenticado
                        null, // No necesitamos contraseña aquí
                        authorities // Usamos las autoridades del token
                );

                // Asociamos la autenticación con la petición actual
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Guardamos la autenticación en el contexto de seguridad de Spring
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continuamos con el resto de filtros en la aplicación
        filterChain.doFilter(request, response);
    }
}
