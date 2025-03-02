package edu.co.sistemagestionempleos.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "candidatos")
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

    public Candidato(Integer id, String nombre, LocalDate fechaRegistro, String educacion,
                     String correo, String habilidades, String experienciaLaboral,
                     String apellido, String telefono, String cvUrl) {
        this.id = id;
        this.nombre = nombre;
        this.fechaRegistro = fechaRegistro;
        this.educacion = educacion;
        this.correo = correo;
        this.habilidades = habilidades;
        this.experienciaLaboral = experienciaLaboral;
        this.apellido = apellido;
        this.telefono = telefono;
        this.cvUrl = cvUrl;
    }

    public Candidato() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCvUrl() {
        return cvUrl;
    }

    public void setCvUrl(String cvUrl) {
        this.cvUrl = cvUrl;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    public String getExperienciaLaboral() {
        return experienciaLaboral;
    }

    public void setExperienciaLaboral(String experienciaLaboral) {
        this.experienciaLaboral = experienciaLaboral;
    }

    public String getEducacion() {
        return educacion;
    }

    public void setEducacion(String educacion) {
        this.educacion = educacion;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
