// Paquete donde se encuentra esta clase dentro del proyecto
package edu.co.sistemagestionempleos.config;

// Importamos las herramientas necesarias para trabajar con tokens JWT
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static javax.crypto.Cipher.SECRET_KEY;

// Indicamos que esta clase es un "servicio" en Spring (una clase que realiza lógica de negocio)
@Service
public class JwtService {

    // Obtenemos el valor de "jwt.secret" del archivo de configuración de la aplicación
    @Value("${jwt.secret}")
    private String secret;

    // Obtenemos el valor de "jwt.expiration", que indica cuánto dura el token antes de expirar
    @Value("${jwt.expiration}")
    private Long expiration;

    // Metodo que extrae el nombre de usuario (username) de un token JWT
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject); // Llama a otro método para extraer la información deseada
    }

    public String extractRole(String token) {
        String role = extractClaim(token, claims -> claims.get("role", String.class));
        System.out.println("Claim 'role' extraído: " + role);
        return role != null ? role : "ROLE_USER";    }

    // Metodo genérico para extraer cualquier dato (claim) de un token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token); // Extrae todos los datos del token
        return claimsResolver.apply(claims); // Devuelve el dato específico que se pidió
    }


    // Metodo que obtiene todos los datos (claims) de un token
    private Claims extractAllClaims(String token) {
        return Jwts // Usamos la librería JWT para procesar el token
                .parserBuilder() // Creamos un "analizador" del token
                .setSigningKey(getSignInKey()) // Indicamos la clave con la que fue firmado el token
                .build() // Construimos el analizador
                .parseClaimsJws(token) // Analizamos el token
                .getBody(); // Obtenemos su contenido (claims)
    }

    // Metodo que genera un token JWT a partir de los datos de un usuario
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(); // Creamos un mapa para guardar información extra
        System.out.println("Authorities: " + userDetails.getAuthorities());
        if (!userDetails.getAuthorities().isEmpty()) {
            claims.put("role", userDetails.getAuthorities().iterator().next().getAuthority());
        } else {
            System.out.println("El usuario no tiene roles asignados.");
            claims.put("role", "ROLE_USER"); // O un valor por defecto
        }
        return generateToken(claims, userDetails); // Llamamos al otro metodo para generar el token con estos datos
    }

    // Metodo que realmente construye el token JWT
    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts // Usamos la librería JWT para construir el token
                .builder() // Empezamos a construir el token
                .setClaims(extraClaims) // Agregamos información extra (como el rol)
                .setSubject(userDetails.getUsername()) // Establecemos el usuario al que pertenece el token
                .setIssuedAt(new Date(System.currentTimeMillis())) // Indicamos la fecha y hora en que se generó
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // Indicamos cuándo expira
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // Firmamos el token con nuestra clave secreta
                .compact(); // Generamos el token en su forma final (cadena de texto)
    }

    // Metodo que verifica si un token es válido para un usuario específico
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token); // Extraemos el nombre de usuario del token
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)); // Comprobamos si es correcto y si aún no ha expirado
    }

    // Metodo que verifica si un token ya expiró
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date()); // Compara la fecha de expiración con la fecha actual
    }

    // Metodo que obtiene la fecha de expiración de un token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration); // Llama al metodo que extrae información del token
    }

    // Metodo que obtiene la clave secreta para firmar/verificar los tokens
    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(secret.getBytes()); // Convierte la clave secreta en un formato adecuado
    }
}
