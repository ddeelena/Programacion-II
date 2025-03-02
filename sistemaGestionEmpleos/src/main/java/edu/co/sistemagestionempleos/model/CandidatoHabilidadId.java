package edu.co.sistemagestionempleos.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CandidatoHabilidadId implements Serializable {

    @Column(name = "candidato_id")
    private Integer candidatoId;

    @Column(name = "habilidad_id")
    private Integer habilidadId;

    public CandidatoHabilidadId() {}

    public CandidatoHabilidadId(Integer candidatoId, Integer habilidadId) {
        this.candidatoId = candidatoId;
        this.habilidadId = habilidadId;
    }

    public Integer getCandidatoId() {
        return candidatoId;
    }

    public void setCandidatoId(Integer candidatoId) {
        this.candidatoId = candidatoId;
    }

    public Integer getHabilidadId() {
        return habilidadId;
    }

    public void setHabilidadId(Integer habilidadId) {
        this.habilidadId = habilidadId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidatoHabilidadId that = (CandidatoHabilidadId) o;
        return Objects.equals(candidatoId, that.candidatoId) &&
                Objects.equals(habilidadId, that.habilidadId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidatoId, habilidadId);
    }
}
