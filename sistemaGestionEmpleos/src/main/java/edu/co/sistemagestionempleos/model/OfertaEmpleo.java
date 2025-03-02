package edu.co.sistemagestionempleos.model;

import edu.co.sistemagestionempleos.enums.EstadoOferta;
import edu.co.sistemagestionempleos.enums.Modalidad;
import edu.co.sistemagestionempleos.enums.TipoJornada;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ofertas_empleo")
public class OfertaEmpleo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    private String titulo;
    private String descripcion;

    private Double salarioMin;
    private Double salarioMax;


/*    @ElementCollection
    @CollectionTable(name = "oferta_requisitos", joinColumns = @JoinColumn(name = "oferta_id"))
    @Column(name = "requisito")*/
    private String requisitos;

    @Enumerated(EnumType.STRING)
    private TipoJornada tipoJornada;

    @Enumerated(EnumType.STRING)
    private Modalidad modalidad;

    private String ubicacion;

    private LocalDate fechaPublicacion;
    private LocalDate fechaExpiracion;

    @Enumerated(EnumType.STRING)
    private EstadoOferta estado;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    public OfertaEmpleo(Integer id, String titulo, String descripcion, Double salarioMin,
                        Double salarioMax, String requisitos, TipoJornada tipoJornada,
                        Modalidad modalidad, String ubicacion, LocalDate fechaPublicacion,
                        LocalDate fechaExpiracion, EstadoOferta estado, Empresa empresa) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.salarioMin = salarioMin;
        this.salarioMax = salarioMax;
        this.requisitos = requisitos;
        this.tipoJornada = tipoJornada;
        this.modalidad = modalidad;
        this.ubicacion = ubicacion;
        this.fechaPublicacion = fechaPublicacion;
        this.fechaExpiracion = fechaExpiracion;
        this.estado = estado;
        this.empresa = empresa;
    }

    public OfertaEmpleo() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getSalarioMin() {
        return salarioMin;
    }

    public void setSalarioMin(Double salarioMin) {
        this.salarioMin = salarioMin;
    }

    public Double getSalarioMax() {
        return salarioMax;
    }

    public void setSalarioMax(Double salarioMax) {
        this.salarioMax = salarioMax;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public TipoJornada getTipoJornada() {
        return tipoJornada;
    }

    public void setTipoJornada(TipoJornada tipoJornada) {
        this.tipoJornada = tipoJornada;
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public EstadoOferta getEstado() {
        return estado;
    }

    public void setEstado(EstadoOferta estado) {
        this.estado = estado;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
