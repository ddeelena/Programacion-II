package edu.co.sistemagestionempleos.model;

import edu.co.sistemagestionempleos.enums.EstadoPostulacion;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "postulaciones")
public class Postulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "candidato_id", nullable = false)
    private Candidato candidato;

    @ManyToOne
    @JoinColumn(name = "oferta_id", nullable = false)
    private OfertaEmpleo ofertaEmpleo;

    private LocalDate fechaPostulacion;

    @Enumerated(EnumType.STRING)
    private EstadoPostulacion estado;

    private String comentarios;

    public Postulacion(Integer id, String comentarios, Candidato candidato, OfertaEmpleo ofertaEmpleo,
                       LocalDate fechaPostulacion, EstadoPostulacion estado) {
        this.id = id;
        this.comentarios = comentarios;
        this.candidato = candidato;
        this.ofertaEmpleo = ofertaEmpleo;
        this.fechaPostulacion = fechaPostulacion;
        this.estado = estado;
    }

    public Postulacion(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public OfertaEmpleo getOfertaEmpleo() {
        return ofertaEmpleo;
    }

    public void setOfertaEmpleo(OfertaEmpleo ofertaEmpleo) {
        this.ofertaEmpleo = ofertaEmpleo;
    }

    public LocalDate getFechaPostulacion() {
        return fechaPostulacion;
    }

    public void setFechaPostulacion(LocalDate fechaPostulacion) {
        this.fechaPostulacion = fechaPostulacion;
    }

    public EstadoPostulacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoPostulacion estado) {
        this.estado = estado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}
