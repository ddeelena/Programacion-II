package edu.co.gestorviajesparcial.model;

import edu.co.gestorviajesparcial.enums.Destinos;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "viajes")
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Destinos destino;

    private int precio;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private int cantidadPersonas;

    @ManyToOne
    @JoinColumn(name = "viajero_id", nullable = false)
    private Viajero viajero;

    public Viaje(Integer id, Destinos destino, int precio, LocalDate fechaInicio,
                 LocalDate fechaFin, int cantidadPersonas, Viajero viajero) {
        this.id = id;
        this.destino = destino;
        this.precio = precio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cantidadPersonas = cantidadPersonas;
        this.viajero = viajero;
    }

    public Viaje() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Destinos getDestino() {
        return destino;
    }

    public void setDestino(Destinos destino) {
        this.destino = destino;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public Viajero getViajero() {
        return viajero;
    }

    public void setViajero(Viajero viajero) {
        this.viajero = viajero;
    }
}
