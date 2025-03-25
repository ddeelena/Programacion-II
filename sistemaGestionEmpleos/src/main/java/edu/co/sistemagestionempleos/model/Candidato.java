package edu.co.sistemagestionempleos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity  // 📌 Indica que esta clase representa una tabla en la base de datos.
@Data    // 📌 Genera automáticamente los métodos como getters, setters y toString.
@Table(name = "candidatos")  // 📌 Especifica que la tabla en la base de datos se llamará "candidatos".
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String cvUrl;

/*    @ElementCollection
    @CollectionTable(name = "candidato_habilidades", joinColumns = @JoinColumn(name = "candidato_id"))
    @Column(name = "habilidad")*/
    private String habilidades;

    private String experienciaLaboral;
    private String educacion;
    private LocalDate fechaRegistro;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"authorities", "password", "enabled", "accountNonExpired", "credentialsNonExpired", "accountNonLocked"})
    private User user;

    public Candidato(Integer id, String nombre, String apellido, String correo, String telefono,
                     String cvUrl, String habilidades, String experienciaLaboral, String educacion,
                     LocalDate fechaRegistro, User user) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.cvUrl = cvUrl;
        this.habilidades = habilidades;
        this.experienciaLaboral = experienciaLaboral;
        this.educacion = educacion;
        this.fechaRegistro = fechaRegistro;
        this.user = user;
    }

    public Candidato() {

    }

}
