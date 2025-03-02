package edu.co.sistemagestionempleos.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

/*@Entity
@Table(name = "candidato_habilidades")*/
public class CandidatoHabilidades {
/*
    @EmbeddedId
    private CandidatoHabilidadId id;

    @ManyToOne
    @MapsId("candidatoId")  // Mapea el candidato_id de la clave compuesta
    @JoinColumn(name = "candidato_id")
    private Candidato candidato;

    @ManyToOne
    @MapsId("habilidadId")  // Mapea el habilidad_id de la clave compuesta
    @JoinColumn(name = "habilidad_id")
    private Habilidad habilidad;

    public CandidatoHabilidades() {}

    public CandidatoHabilidades(Candidato candidato, Habilidad habilidad) {
        this.id = new CandidatoHabilidadId(candidato.getId(), habilidad.getId());
        this.candidato = candidato;
        this.habilidad = habilidad;
    }

    public CandidatoHabilidadId getId() {
        return id;
    }

    public void setId(CandidatoHabilidadId id) {
        this.id = id;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Habilidad getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
    }*/
}
