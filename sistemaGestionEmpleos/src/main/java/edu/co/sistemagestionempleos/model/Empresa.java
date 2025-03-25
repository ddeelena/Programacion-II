package edu.co.sistemagestionempleos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "empresas")
public class Empresa  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String descripcion;
    private String sector;
    private String ubicacion;
    private String correo;
    private LocalDate fecha;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"authorities", "password", "enabled", "accountNonExpired", "credentialsNonExpired", "accountNonLocked"})
    private User user;


    public Empresa() {

    }


}
